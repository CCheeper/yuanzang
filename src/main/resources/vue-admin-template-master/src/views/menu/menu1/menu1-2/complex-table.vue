<template>
	<div class="app-container">
		<div class="filter-container">
			<el-input v-model="listQuery.title" placeholder="Title" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />

			<el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
				Search
			</el-button>
			<el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
				Add
			</el-button>

		</div>
		<br />
		<el-table :key="tableKey" v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%;" @sort-change="sortChange">
			<el-table-column label="ID" prop="id" sortable="custom" align="center" width="80">
				<template slot-scope="scope">
					<span>{{ scope.row.id }}</span>
				</template>
			</el-table-column>
			<el-table-column label="学校名称" width="110px" align="center">
				<template slot-scope="scope">
					<span>{{ scope.row.name }}</span>
				</template>
			</el-table-column>
			<el-table-column label="简介" min-width="150px">
				<template slot-scope="{row}">
					<span class="link-type" @click="handleUpdate(row)">{{ row.jianjie }}</span>
				</template>
			</el-table-column>
			<el-table-column label="学校历史" min-width="150px">
				<template slot-scope="{row}">
					<span class="link-type" @click="handleUpdate(row)">{{ row.history }}</span>
				</template>
			</el-table-column>
			<el-table-column label="是否已经援助" width="110px" align="center">
				<template slot-scope="scope">
					<span>{{ scope.row.isgo }}</span>
				</template>
			</el-table-column>
			<el-table-column label="是否需要援助" width="110px" align="center">
				<template slot-scope="scope">
					<span>{{ scope.row.isneed }}</span>
				</template>
			</el-table-column>

			<el-table-column label="创建日期" width="150px" align="center">
				<template slot-scope="scope">
					<span>{{ scope.row.createTime }}</span>
				</template>
			</el-table-column>
			<el-table-column label="编辑者" width="150px" align="center">
				<template slot-scope="scope">
					<span>{{ scope.row.Editor }}</span>
				</template>
			</el-table-column>
			<el-table-column label="Actions" align="center" width="230" class-name="small-padding fixed-width">
				<template slot-scope="{row}">
					<el-button v-if="row.status!='deleted'" type="primary" size="mini" @click="handleUpdate(row)">
						Edit
					</el-button>

					<el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="handleModifyStatus(row,'deleted')">
						Delete
					</el-button>
				</template>
			</el-table-column>
		</el-table>

		<pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

		<el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
			<el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
				<el-form-item label="学校名称">
					<el-input v-model="temp.name" />
				</el-form-item>
				<el-form-item label="简介">
					<el-input v-model="temp.jianjie" :autosize="{ minRows: 4, maxRows: 6}" type="textarea" placeholder="Please input" />
				</el-form-item>
				<el-form-item label="学校历史">
					<el-input v-model="temp.history" :autosize="{ minRows: 4, maxRows: 6}" type="textarea" placeholder="Please input" />
				</el-form-item>
				<el-form-item label="是否已经援助">
					<el-select v-model="temp.isgo" class="filter-item" placeholder="Please select">
						<el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name" :value="item.key" />
					</el-select>
				</el-form-item>
				<el-form-item label="是否需要援助">
					<el-select v-model="temp.isneed" class="filter-item" placeholder="Please select">
						<el-option v-for="item in statusOptions" :key="item.key" :label="item.display_name" :value="item.key" />
					</el-select>
				</el-form-item>

				<el-form-item v-if=false>
					<el-input v-model="temp.id" />
				</el-form-item>
				<el-form-item v-if=false>
					<el-input v-model="temp.createTime" />
				</el-form-item>
				<el-form-item v-if=false>
					<el-input v-model="temp.editor" />
				</el-form-item>

			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialogFormVisible = false">
					Cancel
				</el-button>
				<el-button type="primary" @click="dialogStatus==='create'?createData('temp'):updateData()">
					Confirm
				</el-button>
			</div>
		</el-dialog>

		<el-dialog :visible.sync="dialogPvVisible" title="Reading statistics">
			<el-table :data="pvData" border fit highlight-current-row style="width: 100%">
				<el-table-column prop="key" label="Channel" />
				<el-table-column prop="pv" label="Pv" />
			</el-table>
			<span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">Confirm</el-button>
      </span>
		</el-dialog>
	</div>
</template>

<script>
	import { fetchList, fetchPv, createArticle, updateArticle } from '@/api/article'
	import waves from '@/directive/waves' // waves directive
	import { parseTime } from '@/utils'
	import Pagination from '@/components/Pagination' // secondary package based on el-pagination
	import axios from 'axios'
	
	
	

	const calendarTypeOptions = [{
			key: 'yes',
			display_name: '是'
		},
		{
			key: 'no',
			display_name: '否'
		},

	]
	const
		statusOptions = [{
			key: 'yes',
			display_name: '是'
		}, {
			key: 'no',
			display_name: '否'
		}]

	// arr to obj, such as { CN : "China", US : "USA" }
	const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
		acc[cur.key] = cur.display_name
		return acc
	}, {})

	export default {
		name: 'ComplexTable',
		components: {
			Pagination
		},
		directives: {
			waves
		},
		filters: {
			statusFilter(status) {
				const statusMap = {
					published: 'success',
					draft: 'info',
					deleted: 'danger'
				}
				return statusMap[status]
			},
			typeFilter(type) {
				return calendarTypeKeyValue[type]
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
					title: undefined,
					type: undefined,
					sort: '+id'
				},
				importanceOptions: [1, 2, 3],
				calendarTypeOptions,
				sortOptions: [{
					label: 'ID Ascending',
					key: '+id'
				}, {
					label: 'ID Descending',
					key: '-id'
				}],
				statusOptions,
				showReviewer: false,
				temp: {
					id: undefined,
					remark: '',
					createTime: '',
					title: '',
					type: '',
					status: 'published',
					editor: ''
				},
				dialogFormVisible: false,
				dialogStatus: '',
				textMap: {
					update: 'Edit',
					create: 'Create'
				},
				dialogPvVisible: false,
				pvData: [],
				rules: {
					type: [{
						required: true,
						message: 'type is required',
						trigger: 'change'
					}],
					timestamp: [{
						type: 'date',
						required: true,
						message: 'timestamp is required',
						trigger: 'change'
					}],
					title: [{
						required: true,
						message: 'title is required',
						trigger: 'blur'
					}]
				},
				downloadLoading: false
			}
		},
		created() {
			this.getList()
		},
		methods: {
			getList(data) {
				this.listLoading = true
				fetchList(this.listQuery).then(response => {
					console.log(data)
					console.log(response.data)
					this.list = response.data.items
					this.total = response.data.total

					// Just to simulate the time of the request
					setTimeout(() => {
						this.listLoading = false
					}, 1.5 * 1000)
				})
			},
			handleFilter() {
				this.listQuery.page = 1
				this.getList()
			},
			handleModifyStatus(row, status) {
				this.$message({
					message: '操作Success',
					type: 'success'
				})
				console.log("sss")
				axios.post('/message/isSuccess', {
						status: 'delete',
						id: row.id
					})
					.then(function(response) {
						console.log(response);
					})
					.catch(function(error) {
						console.log(error);
					});
				row.status = status

			},
			sortChange(data) {
				const {
					prop,
					order
				} = data
				if(prop === 'id') {
					this.sortByID(order)
				}
			},
			sortByID(order) {
				if(order === 'ascending') {
					this.listQuery.sort = '+id'
				} else {
					this.listQuery.sort = '-id'
				}
				this.handleFilter()
			},

	
			
			resetTemp() {

				this.temp = {

					id: '',
					remark: '',
					createTime: '',
					title: '',
					status: 'published',
					type: '',
					editor:''
				}
			
				var this_ = this
				axios.get('/message/getthings')
					.then(function(response) {
						console.log(response);
						this_.temp.id = response.data.id
						this_.temp.createTime = response.data.date
						this_.temp.editor = response.data.editor
					})
					.catch(function(error) {
						console.log(error);
					});
			},
			handleCreate() {
				this.resetTemp()
				this.dialogStatus = 'create'
				this.dialogFormVisible = true
				this.$nextTick(() => {
					this.$refs['dataForm'].clearValidate()
				})

			},
			createData(temp) {
				this.$refs['dataForm'].validate((valid) => {
					if(valid) {

						createArticle(this.temp).then(() => {

							this.list.unshift(this.temp)
							this.dialogFormVisible = false
							this.$notify({
								title: 'Success',
								message: 'Created Successfully',
								type: 'success',
								duration: 2000
							})
						})
					}
				})
			},
			handleUpdate(row) {
				this.temp = Object.assign({}, row) // copy obj
				this.temp.timestamp = new Date(this.temp.timestamp)
				this.dialogStatus = 'update'
				this.dialogFormVisible = true
				this.$nextTick(() => {
					this.$refs['dataForm'].clearValidate()
				})
			},
			updateData() {
				this.$refs['dataForm'].validate((valid) => {
					if(valid) {
						const tempData = Object.assign({}, this.temp)
						tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
						updateArticle(tempData).then(() => {
							for(const v of this.list) {
								if(v.id === this.temp.id) {
									const index = this.list.indexOf(v)
									this.list.splice(index, 1, this.temp)
									break
								}
							}
							this.dialogFormVisible = false
							this.$notify({
								title: 'Success',
								message: 'Update Successfully',
								type: 'success',
								duration: 2000
							})
						})
					}
				})
			},
			handleDelete(row) {
				this.$notify({
					title: 'Success',
					message: 'Delete Successfully',
					type: 'success',
					duration: 2000
				})
				const index = this.list.indexOf(row)
				this.list.splice(index, 1)
			},
			handleFetchPv(pv) {
				fetchPv(pv).then(response => {
					this.pvData = response.data.pvData
					this.dialogPvVisible = true
				})
			},
			handleDownload() {
				this.downloadLoading = true
				import('@/vendor/Export2Excel').then(excel => {
					const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
					const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
					const data = this.formatJson(filterVal, this.list)
					excel.export_json_to_excel({
						header: tHeader,
						data,
						filename: 'table-list'
					})
					this.downloadLoading = false
				})
			},
			formatJson(filterVal, jsonData) {
				return jsonData.map(v => filterVal.map(j => {
					if(j === 'timestamp') {
						return parseTime(v[j])
					} else {
						return v[j]
					}
				}))
			}
		}
	}
</script>