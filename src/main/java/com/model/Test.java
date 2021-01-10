package com.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table
public class Test
{
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    protected long id;
    @Column
    protected Date datum;
    @Column
    protected int ukupanBrojBodova;
    @Column
    protected int brojOstvarenihBodova;
    @OneToMany
    protected Set<Pitanje> pitanja;
    @ManyToOne
    protected User kreator;

    public Test()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public Date getDatum()
    {
        return datum;
    }

    public void setDatum(Date datum)
    {
        this.datum = datum;
    }

    public int getUkupanBrojBodova() { return ukupanBrojBodova; }

    public void setUkupanBrojBodova(int ukupanBrojBodova) { this.ukupanBrojBodova = ukupanBrojBodova; }

    public int getBrojOstvarenihBodova() { return brojOstvarenihBodova; }

    public void setBrojOstvarenihBodova(int brojOstvarenihBodova) { this.brojOstvarenihBodova = brojOstvarenihBodova; }

    public Set<Pitanje> getPitanja(){ return pitanja; }

    public void setPitanja(Set<Pitanje> pitanja) { this.pitanja = pitanja;}

    public User getKreator() { return kreator; }

    public void setKreator(User kreator) { this.kreator = kreator; }
}
