package com.example.yuanz.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jurisdiction", schema = "yuanzang", catalog = "")
public class JurisdictionEntity {
    private String id;
    private Byte jurisdiction1;
    private Byte jurisdiction2;
    private Byte jurisdiction3;
    private Byte jurisdiction4;
    private Byte jurisdiction5;
    private Byte jurisdiction6;
    private Byte jurisdiction7;
    private Byte jurisdiction8;
    private Byte jurisdiction9;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "jurisdiction1")
    public Byte getJurisdiction1() {
        return jurisdiction1;
    }

    public void setJurisdiction1(Byte jurisdiction1) {
        this.jurisdiction1 = jurisdiction1;
    }

    @Basic
    @Column(name = "jurisdiction2")
    public Byte getJurisdiction2() {
        return jurisdiction2;
    }

    public void setJurisdiction2(Byte jurisdiction2) {
        this.jurisdiction2 = jurisdiction2;
    }

    @Basic
    @Column(name = "jurisdiction3")
    public Byte getJurisdiction3() {
        return jurisdiction3;
    }

    public void setJurisdiction3(Byte jurisdiction3) {
        this.jurisdiction3 = jurisdiction3;
    }

    @Basic
    @Column(name = "jurisdiction4")
    public Byte getJurisdiction4() {
        return jurisdiction4;
    }

    public void setJurisdiction4(Byte jurisdiction4) {
        this.jurisdiction4 = jurisdiction4;
    }

    @Basic
    @Column(name = "jurisdiction5")
    public Byte getJurisdiction5() {
        return jurisdiction5;
    }

    public void setJurisdiction5(Byte jurisdiction5) {
        this.jurisdiction5 = jurisdiction5;
    }

    @Basic
    @Column(name = "jurisdiction6")
    public Byte getJurisdiction6() {
        return jurisdiction6;
    }

    public void setJurisdiction6(Byte jurisdiction6) {
        this.jurisdiction6 = jurisdiction6;
    }

    @Basic
    @Column(name = "jurisdiction7")
    public Byte getJurisdiction7() {
        return jurisdiction7;
    }

    public void setJurisdiction7(Byte jurisdiction7) {
        this.jurisdiction7 = jurisdiction7;
    }

    @Basic
    @Column(name = "jurisdiction8")
    public Byte getJurisdiction8() {
        return jurisdiction8;
    }

    public void setJurisdiction8(Byte jurisdiction8) {
        this.jurisdiction8 = jurisdiction8;
    }

    @Basic
    @Column(name = "jurisdiction9")
    public Byte getJurisdiction9() {
        return jurisdiction9;
    }

    public void setJurisdiction9(Byte jurisdiction9) {
        this.jurisdiction9 = jurisdiction9;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JurisdictionEntity that = (JurisdictionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(jurisdiction1, that.jurisdiction1) &&
                Objects.equals(jurisdiction2, that.jurisdiction2) &&
                Objects.equals(jurisdiction3, that.jurisdiction3) &&
                Objects.equals(jurisdiction4, that.jurisdiction4) &&
                Objects.equals(jurisdiction5, that.jurisdiction5) &&
                Objects.equals(jurisdiction6, that.jurisdiction6) &&
                Objects.equals(jurisdiction7, that.jurisdiction7) &&
                Objects.equals(jurisdiction8, that.jurisdiction8) &&
                Objects.equals(jurisdiction9, that.jurisdiction9);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jurisdiction1, jurisdiction2, jurisdiction3, jurisdiction4, jurisdiction5, jurisdiction6, jurisdiction7, jurisdiction8, jurisdiction9);
    }
}
