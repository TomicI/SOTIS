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
    protected Integer x0;
    @Column
    protected Integer y0;
    @Column
    protected Integer width;
    @Column
    protected Integer height;
    @Column
    protected Long pitanjeId;

    public Regioni()
    {
    }

    public long getId()
    {
        if (id != null)
            return id;
        else
            return 0;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Integer getX0()
    {
        return x0;
    }

    public void setX0(Integer x1)
    {
        this.x0 = x1;
    }

    public Integer getY0()
    {
        return y0;
    }

    public void setY0(Integer y1)
    {
        this.y0 = y1;
    }

    public Integer getWidth()
    {
        return width;
    }

    public void setWidth(Integer x2)
    {
        this.width = x2;
    }

    public Integer getHeight()
    {
        return height;
    }

    public void setHeight(Integer y2)
    {
        this.height = y2;
    }

    public Long getPitanjeId()
    {
        return pitanjeId;
    }

    public void setPitanjeId(Long pitanje)
    {
        this.pitanjeId = pitanje;
    }
}
