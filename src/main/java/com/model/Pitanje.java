package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Pitanje
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    protected int redniBroj;
    @Column
    protected String tekstPitanja;
    @Column
    protected Long creatorId;
    @OneToMany
    protected Set<Odgovor> odgovori;
    @OneToMany
    protected Set<Regioni> regioni;

    public Pitanje()
    {
    }

    public Long getId() {return id; }

    public void setId(long id) { this.id = id; }

    public int getRedniBroj(){ return redniBroj; }

    public void setRedniBroj(int redniBroj)
    {
        this.redniBroj = redniBroj;
    }

    public String getTekstPitanja()
    {
        return tekstPitanja;
    }

    public void setTekstPitanja(String tekstPitanja)
    {
        this.tekstPitanja = tekstPitanja;
    }

    public Set<Odgovor> getOdgovori()
    {
        return odgovori;
    }

    public void setOdgovori(Set<Odgovor> odgovori)
    {
        this.odgovori = odgovori;
    }

    public Set<Regioni> getRegioni() { return regioni; }

    public void setRegioni(Set<Regioni> regioni) { this.regioni = regioni; }

    public Long getCreatorId() { return creatorId; }

    public void setCreatorId (Long creatorId) { this.creatorId = creatorId; }

    @Override
    public String toString() {
        return "Pitanje{" +
                "id=" + id +
                ", redniBroj=" + redniBroj +
                ", tekstPitanja='" + tekstPitanja + '\'' +
                ", regioni= " + regioni.size() == null ? "0" : String.valueOf(regioni.size() +
                '}');
    }
}
