package com.example.yuanz.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "road", schema = "yuanzang", catalog = "")
public class RoadEntity {
    private String id;
    private String route;
    private String title;
    private String schoolid;
    private String time;
    private String other;
    private String editorid;
    private String schoolname;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "route")
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
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
    @Column(name = "schoolid")
    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
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
        RoadEntity that = (RoadEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(route, that.route) &&
                Objects.equals(title, that.title) &&
                Objects.equals(schoolid, that.schoolid) &&
                Objects.equals(time, that.time) &&
                Objects.equals(other, that.other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, route, title, schoolid, time, other);
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
    @Column(name = "schoolname")
    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }
}
