package com.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Pitanje
{
    @Id
    protected long id;
    @Column
    protected int redniBroj;
    @Column
    protected String tekstPitanja;
    @OneToMany
    protected Set<Odgovor> odgovori;

    public Pitanje()
    {
    }

    public long getId() {return id; }

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
}
