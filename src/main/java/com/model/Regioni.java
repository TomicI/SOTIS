package com.model;

import javax.persistence.*;

@Entity
@Table
public class Regioni
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    protected double x1;
    @Column
    protected double y1;
    @Column
    protected double x2;
    @Column
    protected double y2;
    @ManyToOne
    protected Pitanje pitanje;

    public Regioni()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public double getX1()
    {
        return x1;
    }

    public void setX1(double x1)
    {
        this.x1 = x1;
    }

    public double getY1()
    {
        return y1;
    }

    public void setY1(double y1)
    {
        this.y1 = y1;
    }

    public double getX2()
    {
        return x2;
    }

    public void setX2(double x2)
    {
        this.x2 = x2;
    }

    public double getY2()
    {
        return y2;
    }

    public void setY2(double y2)
    {
        this.y2 = y2;
    }

    public Pitanje getPitanje()
    {
        return pitanje;
    }

    public void setPitanje(Pitanje pitanje)
    {
        this.pitanje = pitanje;
    }
}
