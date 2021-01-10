package com.model;

import javax.persistence.*;

@Entity
@Table
public class Odgovor
{
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    protected long id;
    @Column
    protected String tekstOdgovora;
    @Column
    protected boolean tacan;
    @Column
    protected int brojBodova;
    @ManyToOne
    protected Pitanje pitanje;

    public Odgovor()
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

    public String getTekstOdgovora()
    {
        return tekstOdgovora;
    }

    public void setTekstOdgovora(String tekstOdgovora)
    {
        this.tekstOdgovora = tekstOdgovora;
    }

    public boolean isTacan()
    {
        return tacan;
    }

    public void setTacan(boolean tacan)
    {
        this.tacan = tacan;
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
