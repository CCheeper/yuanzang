<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.title"
        placeholder=""
        style="width: 200px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />

      <el-button
        v-waves
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="handleFilter"
      >Search</el-button>
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate"
      >Add</el-button>
    </div>
    <br>
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <el-table-column label="ID" prop="id" sortable="custom" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="标题" width="150px" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column label="文件名" min-width="150px">
        <template slot-scope="{row}">
          <span>
            <a>{{ row.fileurl }}</a>
          </span>
        </template>
      </el-table-column>
       <el-table-column label="编辑者" width="150px" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.Editor }}</span>
        </template>
      </el-table-column>

      <el-table-column label="创建日期" width="150px" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="Actions"
        align="center"
        width="230"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="{row}">
          <el-button
            v-if="row.status!='deleted'"
            type="primary"
            size="mini"
            @click="handleUpdate(row)"
          >Edit</el-button>

          <el-button
            v-if="row.status!='deleted'"
            size="mini"
            type="danger"
            @click="handleModifyStatus(row,'deleted')"
          >Delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form
        ref="dataForm"
        :model="temp"
        label-position="left"
        label-width="70px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="标题">
          <el-input v-model="temp.title"/>
        </el-form-item>
        <el-form-item label="上传文件">
          <el-upload
            class="upload-demo"
            ref="upload"
            action="/upload"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :auto-upload="false"
            :on-progress="onProgress"
          >
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button
              style="margin-left: 10px;"
              size="small"
              type="success"
              @click="submitUpload"
            >上传到服务器</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>

        <el-form-item v-if="false">
          <el-input v-model="temp.id"/>
        </el-form-item>
        <el-form-item v-if="false">
          <el-input v-model="temp.createTime"/>
        </el-form-item>
        <el-form-item v-if="false">
          <el-input v-model="temp.editor"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button
          type="primary"
          @click="dialogStatus==='create'?createData('temp'):updateData()"
        >Confirm</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogPvVisible" title="Reading statistics">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel"/>
        <el-table-column prop="pv" label="Pv"/>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { fetchList, fetchPv, createPolicy, updatePolicy,uploadfile } from "@/api/policy";
import waves from "@/directive/waves"; // waves directive
import { parseTime } from "@/utils";
import Pagination from "@/components/Pagination"; // secondary package based on el-pagination
import axios from "axios";
import Cookies from 'js-cookie'
import { request } from 'http';

const calendarTypeOptions = [
  {
    key: "yes",
    display_name: "是"
  },
  {
    key: "no",
    display_name: "否"
  }
];
const statusOptions = [
  {
    key: "yes",
    display_name: "是"
  },
  {
    key: "no",
    display_name: "否"
  }
];

// arr to obj, such as { CN : "China", US : "USA" }
const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name;
  return acc;
}, {});

export default {
  name: "ComplexTable",
  components: {
    Pagination
  },
  directives: {
    waves
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: "success",
        draft: "info",
        deleted: "danger"
      };
      return statusMap[status];
    },
    typeFilter(type) {
      return calendarTypeKeyValue[type];
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: '',
        type: undefined,
        sort: "+id"
      },
      importanceOptions: [1, 2, 3],
      calendarTypeOptions,
      sortOptions: [
        {
          label: "ID Ascending",
          key: "+id"
        },
        {
          label: "ID Descending",
          key: "-id"
        }
      ],
      statusOptions,
      showReviewer: false,
      temp: {
        id: undefined,
        remark: "",
        createTime: "",
        title: "",
        type: "",
        status: "published",
        editor: ""
      },
      dialogFormVisible: false,
      dialogStatus: "",
      textMap: {
        update: "Edit",
        create: "Create"
      },
      dialogPvVisible: false,
      pvData: [],

      downloadLoading: false,

    };
  },
  created() {
    this.getList();
  },
  methods: {


    submitUpload() {
      this.$refs.upload.submit();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },


    onProgress(event, file, fileList) {
      this.listLoading = true;
      console.log(file)
      uploadfile(file).then(request => {
        var ssssss=this.file.name

        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false;
        }, 1.5 * 1000);
      });
    },


    getList(data) {
      this.listLoading = true;
      fetchList(this.listQuery).then(response => {
        console.log(data);
        console.log(response.data);
        this.list = response.data.items;
        this.total = response.data.total;

        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false;
        }, 1.5 * 1000);
      });
    },
    handleFilter() {
      this.listQuery.page = 1;
      var title;
      title = this.listQuery.title;
      this.getList(title);
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: "操作Success",
        type: "success"
      });
      console.log("sss");
      axios
        .post("/policy/delect", {
          status: "delete",
          id: row.id
        })
        .then(function(response) {
          console.log(response);
        })
        .catch(function(error) {
          console.log(error);
        });
      row.status = status;
    },
    sortChange(data) {
      const { prop, order } = data;
      if (prop === "id") {
        this.sortByID(order);
      }
    },
    sortByID(order) {
      if (order === "ascending") {
        this.listQuery.sort = "+id";
      } else {
        this.listQuery.sort = "-id";
      }
      this.handleFilter();
    },

    resetTemp() {},
    handleCreate() {
      this.resetTemp();

      this.temp = {
        id: "",
        remark: "",
        createTime: "",
        title: "",
        status: "published",
        type: "",
        editor: ""
      };

      var this_ = this;
      axios
        .get("/policy/getthings")
        .then(function(response) {
          this_.temp.id = response.data.id;
          this_.temp.createTime = response.data.date;
          this_.temp.editor = Cookies.get("username");      //前端更新显示，并未从后端获取，但是数据和后台一样
          console.log(Cookies.get("username") + "asdasdasd");
        })
        .catch(function(error) {
          console.log(error);
        });

      this.dialogStatus = "create";
      this.dialogFormVisible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].clearValidate();
      });
    },
    createData(temp) {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          createPolicy(this.temp).then(() => {
            
            this.list.unshift(this.temp);
            this.dialogFormVisible = false;
            this.$notify({
              title: "Success",
              message: "Created Successfully",
              type: "success",
              duration: 2000
            });
          });
        }
      });
    },
    handleUpdate(row) {
      var this_ = this
      this.temp = Object.assign({}, row); // copy obj

      this_.temp.Editor = Cookies.get("username");

      this.dialogStatus = "update";
      this.dialogFormVisible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].clearValidate();
      });
    },
    updateData() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          const tempData = Object.assign({}, this.temp);
          // tempData.timestamp = +new Date(tempData.timestamp); // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
          updatePolicy(tempData).then(() => {
            for (const v of this.list) {
              if (v.id === this.temp.id) {
                const index = this.list.indexOf(v);
                this.list.splice(index, 1, this.temp);
                break;
              }
            }
            this.dialogFormVisible = false;
            this.$notify({
              title: "Success",
              message: "Update Successfully",
              type: "success",
              duration: 2000
            });
          });
        }
      });
    },

    handleFetchPv(pv) {
      fetchPv(pv).then(response => {
        this.pvData = response.data.pvData;
        this.dialogPvVisible = true;
      });
    },

    formatJson(filterVal, jsonData) {
      return jsonData.map(v =>
        filterVal.map(j => {
          if (j === "timestamp") {
            return parseTime(v[j]);
          } else {
            return v[j];
          }
        })
      );
    }
  }
};
</script>
