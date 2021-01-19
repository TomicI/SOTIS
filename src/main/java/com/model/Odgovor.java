package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Odgovor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    protected String tekstOdgovora;
    @Column
    protected boolean tacan;
    @Column
    protected int brojBodova;
    @JsonIgnore
    @ManyToOne
    protected Pitanje pitanje;

    public Odgovor()
    {
    }

    public Long getId()
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

    public int getBrojBodova() { return brojBodova; }

    public void setBrojBodova(int brojBodova) { this.brojBodova = brojBodova; }
}
