package com.example.yuanz.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "power_edit", schema = "yuanzang", catalog = "")
public class PowerEditEntity {
    private String adminId;
    private Integer powerEdit;
    private Integer roleEdit;
    private Integer schoolEdit;
    private Integer roadEdit;
    private Integer workdataEdit;
    private Integer helpZEdit;
    private Integer helppeopleEdit;
    private Integer personalEdit;
    private Integer needEdit;
    private String time;
    private String editor;

    @Id
    @Column(name = "admin_id")
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "power_edit")
    public Integer getPowerEdit() {
        return powerEdit;
    }

    public void setPowerEdit(Integer powerEdit) {
        this.powerEdit = powerEdit;
    }

    @Basic
    @Column(name = "role_edit")
    public Integer getRoleEdit() {
        return roleEdit;
    }

    public void setRoleEdit(Integer roleEdit) {
        this.roleEdit = roleEdit;
    }

    @Basic
    @Column(name = "school_edit")
    public Integer getSchoolEdit() {
        return schoolEdit;
    }

    public void setSchoolEdit(Integer schoolEdit) {
        this.schoolEdit = schoolEdit;
    }

    @Basic
    @Column(name = "road_edit")
    public Integer getRoadEdit() {
        return roadEdit;
    }

    public void setRoadEdit(Integer roadEdit) {
        this.roadEdit = roadEdit;
    }

    @Basic
    @Column(name = "workdata_edit")
    public Integer getWorkdataEdit() {
        return workdataEdit;
    }

    public void setWorkdataEdit(Integer workdataEdit) {
        this.workdataEdit = workdataEdit;
    }

    @Basic
    @Column(name = "helpZ_edit")
    public Integer getHelpZEdit() {
        return helpZEdit;
    }

    public void setHelpZEdit(Integer helpZEdit) {
        this.helpZEdit = helpZEdit;
    }

    @Basic
    @Column(name = "helppeople_edit")
    public Integer getHelppeopleEdit() {
        return helppeopleEdit;
    }

    public void setHelppeopleEdit(Integer helppeopleEdit) {
        this.helppeopleEdit = helppeopleEdit;
    }

    @Basic
    @Column(name = "personal_edit")
    public Integer getPersonalEdit() {
        return personalEdit;
    }

    public void setPersonalEdit(Integer personalEdit) {
        this.personalEdit = personalEdit;
    }

    @Basic
    @Column(name = "need_edit")
    public Integer getNeedEdit() {
        return needEdit;
    }

    public void setNeedEdit(Integer needEdit) {
        this.needEdit = needEdit;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "editor")
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerEditEntity that = (PowerEditEntity) o;
        return Objects.equals(adminId, that.adminId) &&
                Objects.equals(powerEdit, that.powerEdit) &&
                Objects.equals(roleEdit, that.roleEdit) &&
                Objects.equals(schoolEdit, that.schoolEdit) &&
                Objects.equals(roadEdit, that.roadEdit) &&
                Objects.equals(workdataEdit, that.workdataEdit) &&
                Objects.equals(helpZEdit, that.helpZEdit) &&
                Objects.equals(helppeopleEdit, that.helppeopleEdit) &&
                Objects.equals(personalEdit, that.personalEdit) &&
                Objects.equals(needEdit, that.needEdit) &&
                Objects.equals(time, that.time) &&
                Objects.equals(editor, that.editor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, powerEdit, roleEdit, schoolEdit, roadEdit, workdataEdit, helpZEdit, helppeopleEdit, personalEdit, needEdit, time, editor);
    }
}
