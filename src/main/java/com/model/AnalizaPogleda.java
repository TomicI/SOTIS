package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table
public class AnalizaPogleda
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    protected Double x;
    @Column
    protected Double y;
    @ManyToOne
    protected Pitanje pitanje;
    @JsonIgnore
    @ManyToOne
    protected ReseniTest reseniTest;
    @Column
    protected Long regionId;
    @Column(nullable = false)
    protected Timestamp timestamp;

    public AnalizaPogleda() { }

    public AnalizaPogleda(Double x, Double y, Pitanje pitanje, ReseniTest reseniTest, Long regionId, Timestamp timestamp) {
        this.x = x;
        this.y = y;
        this.pitanje = pitanje;
        this.reseniTest = reseniTest;
        this.regionId = regionId;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Pitanje getPitanje() {
        return pitanje;
    }

    public void setPitanje(Pitanje pitanje) {
        this.pitanje = pitanje;
    }

    public ReseniTest getReseniTest() {
        return reseniTest;
    }

    public void setReseniTest(ReseniTest reseniTest) {
        this.reseniTest = reseniTest;
    }

    public Long getRegionId()
    {
        return regionId;
    }

    public void setRegionId(Long regionId)
    {
        this.regionId = regionId;
    }
}
