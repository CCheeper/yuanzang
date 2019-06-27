package com.example.yuanz.entity;

import javax.persistence.*;
import java.util.Objects;
/**
 * Demo class
 *
 * @author creeper
 * @date 2016/10/31
 */
@Entity
@Table(name = "user", schema = "yuanzang", catalog = "")
public class UserEntity {
    private String id;
    private String username;
    private String password;
    private Integer level;
    private String telephone;
    private String qq;
    private String email;
    private String schoolId;
    private String schoolName;
    private String address;
    private String gender;
    private String other;
    private String recruitId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "school_id")
    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @Basic
    @Column(name = "school_name")
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "other")
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(level, that.level) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(qq, that.qq) &&
                Objects.equals(email, that.email) &&
                Objects.equals(schoolId, that.schoolId) &&
                Objects.equals(schoolName, that.schoolName) &&
                Objects.equals(address, that.address) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(other, that.other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, level, telephone, qq, email, schoolId, schoolName, address, gender, other);
    }

    @Basic
    @Column(name = "recruit_id")
    public String getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(String recruitId) {
        this.recruitId = recruitId;
    }
}
