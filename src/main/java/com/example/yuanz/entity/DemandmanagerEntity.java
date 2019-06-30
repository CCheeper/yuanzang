package com.example.yuanz.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "demandmanager", schema = "yuanzang", catalog = "")
public class DemandmanagerEntity {
    private String id;
    private String title;//标题
    private String ugent;//紧急状态
    private String schoolName;
    private String message;//援助信息
    private String createTime;//创建时间
    private String editor;//编辑者
    private String other;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "ugent")
    public String getUgent() {
        return ugent;
    }

    public void setUgent(String ugent) {
        this.ugent = ugent;
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
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "create_time")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "editor")
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemandmanagerEntity that = (DemandmanagerEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(ugent, that.ugent) &&
                Objects.equals(schoolName, that.schoolName) &&
                Objects.equals(message, that.message) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(other, that.other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, ugent, schoolName, message, createTime, editor, other);
    }
}
