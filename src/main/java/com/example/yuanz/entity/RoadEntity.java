package com.example.yuanz.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "road", schema = "yuanzang", catalog = "")
public class RoadEntity {
    private String id;
    private String editor;
    private String route;
    private String schoolname;
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
    @Column(name = "editor")
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
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
    @Column(name = "schoolname")
    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
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
                Objects.equals(editor, that.editor) &&
                Objects.equals(route, that.route) &&
                Objects.equals(schoolname, that.schoolname) &&
                Objects.equals(time, that.time) &&
                Objects.equals(other, that.other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, editor, route, schoolname, time, other);
    }
}
