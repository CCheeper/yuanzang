package com.example.yuanz.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "policy", schema = "yuanzang", catalog = "")
public class PolicyEntity {
    private String id;
    private String title;
    private String fileurl;
    private String editorid;
    private String time;
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
    @Column(name = "fileurl")
    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    @Basic
    @Column(name = "editorid")
    public String getEditorid() {
        return editorid;
    }

    public void setEditorid(String editorid) {
        this.editorid = editorid;
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
        PolicyEntity that = (PolicyEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(fileurl, that.fileurl) &&
                Objects.equals(editorid, that.editorid) &&
                Objects.equals(time, that.time) &&
                Objects.equals(other, that.other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, fileurl, editorid, time, other);
    }
}
