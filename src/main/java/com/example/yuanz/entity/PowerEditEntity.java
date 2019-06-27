package com.example.yuanz.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "power_edit", schema = "yuanzang", catalog = "")
public class PowerEditEntity {
    private String powerId;
    private String adminId;
    private String powerEdit;
    private String roleEdit;
    private String schoolEdit;
    private String roadEdit;
    private String workdataEdit;
    private String helpZEdit;
    private String helppeopleEdit;
    private String personalEdit;

    @Id
    @Column(name = "power_id")
    public String getPowerId() {
        return powerId;
    }

    public void setPowerId(String powerId) {
        this.powerId = powerId;
    }

    @Basic
    @Column(name = "admin_id")
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "power_edit")
    public String getPowerEdit() {
        return powerEdit;
    }

    public void setPowerEdit(String powerEdit) {
        this.powerEdit = powerEdit;
    }

    @Basic
    @Column(name = "role_edit")
    public String getRoleEdit() {
        return roleEdit;
    }

    public void setRoleEdit(String roleEdit) {
        this.roleEdit = roleEdit;
    }

    @Basic
    @Column(name = "school_edit")
    public String getSchoolEdit() {
        return schoolEdit;
    }

    public void setSchoolEdit(String schoolEdit) {
        this.schoolEdit = schoolEdit;
    }

    @Basic
    @Column(name = "road_edit")
    public String getRoadEdit() {
        return roadEdit;
    }

    public void setRoadEdit(String roadEdit) {
        this.roadEdit = roadEdit;
    }

    @Basic
    @Column(name = "workdata_edit")
    public String getWorkdataEdit() {
        return workdataEdit;
    }

    public void setWorkdataEdit(String workdataEdit) {
        this.workdataEdit = workdataEdit;
    }

    @Basic
    @Column(name = "helpZ_edit")
    public String getHelpZEdit() {
        return helpZEdit;
    }

    public void setHelpZEdit(String helpZEdit) {
        this.helpZEdit = helpZEdit;
    }

    @Basic
    @Column(name = "helppeople_edit")
    public String getHelppeopleEdit() {
        return helppeopleEdit;
    }

    public void setHelppeopleEdit(String helppeopleEdit) {
        this.helppeopleEdit = helppeopleEdit;
    }

    @Basic
    @Column(name = "personal_edit")
    public String getPersonalEdit() {
        return personalEdit;
    }

    public void setPersonalEdit(String personalEdit) {
        this.personalEdit = personalEdit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerEditEntity that = (PowerEditEntity) o;
        return Objects.equals(powerId, that.powerId) &&
                Objects.equals(adminId, that.adminId) &&
                Objects.equals(powerEdit, that.powerEdit) &&
                Objects.equals(roleEdit, that.roleEdit) &&
                Objects.equals(schoolEdit, that.schoolEdit) &&
                Objects.equals(roadEdit, that.roadEdit) &&
                Objects.equals(workdataEdit, that.workdataEdit) &&
                Objects.equals(helpZEdit, that.helpZEdit) &&
                Objects.equals(helppeopleEdit, that.helppeopleEdit) &&
                Objects.equals(personalEdit, that.personalEdit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(powerId, adminId, powerEdit, roleEdit, schoolEdit, roadEdit, workdataEdit, helpZEdit, helppeopleEdit, personalEdit);
    }
}
