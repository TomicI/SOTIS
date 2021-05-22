package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table
public class Test
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    protected Date datum;
    @Column
    protected int ukupanBrojBodova;
    @OneToMany
    protected Set<Pitanje> pitanja;
    @ManyToOne
    protected User kreator;

    public Test()
    {
    }

    public long getId()
    {
        if (id != null)
            return id;
        else
            return 0;
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

    public Set<Pitanje> getPitanja(){ return pitanja; }

    public void setPitanja(Set<Pitanje> pitanja) { this.pitanja = pitanja;}

    public User getKreator() { return kreator; }

    public void setKreator(User kreator) { this.kreator = kreator; }

    @Override
    public String toString() {
        return "Test{" +
                "pitanja=" + pitanja.size() +
                ", kreator=" + kreator.getUsername() +
                '}';
    }
}
