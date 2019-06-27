package com.example.yuanz.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "school", schema = "yuanzang", catalog = "")
public class SchoolEntity {
    private String id;
    private String schoolName;
    private String info;
    private String isGo;
    private String city;
    private String isNeed;
    private String history;
    private String other;
    private String createTime;
    private String editorId;
    private String editor;

    @Id
    @Column(name = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "school_Name")
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "is_Go")
    public String getIsGo() {
        return isGo;
    }

    public void setIsGo(String isGo) {
        this.isGo = isGo;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "is_need")
    public String getIsNeed() {
        return isNeed;
    }

    public void setIsNeed(String isNeed) {
        this.isNeed = isNeed;
    }

    @Basic
    @Column(name = "history")
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @Basic
    @Column(name = "other")
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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
    @Column(name = "editorId")
    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
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
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        SchoolEntity that = (SchoolEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(schoolName, that.schoolName) &&
                Objects.equals(info, that.info) &&
                Objects.equals(isGo, that.isGo) &&
                Objects.equals(city, that.city) &&
                Objects.equals(isNeed, that.isNeed) &&
                Objects.equals(history, that.history) &&
                Objects.equals(other, that.other) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(editorId, that.editorId) &&
                Objects.equals(editor, that.editor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schoolName, info, isGo, city, isNeed, history, other, createTime, editorId, editor);
    }
}
