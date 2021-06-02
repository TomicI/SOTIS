package com.model;

import javax.persistence.*;

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
    @ManyToOne
    protected ReseniTest reseniTest;

    public AnalizaPogleda() { }

    public AnalizaPogleda(Double x, Double y, Pitanje pitanje, ReseniTest reseniTest) {
        this.x = x;
        this.y = y;
        this.pitanje = pitanje;
        this.reseniTest = reseniTest;
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
}
