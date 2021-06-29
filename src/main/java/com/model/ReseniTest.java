package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table
public class ReseniTest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    protected Timestamp datumVremePocetak;
    @Column
    protected Timestamp datumVremeKraj;
    @Column
    protected int brojOstvarenihBodova;
    @ManyToOne
    protected Test test;
    @ManyToOne
    protected User ucenik;
    @OneToMany
    protected Set<Odgovor> odgovorSet;

    @OneToMany
    protected Set<EyetrackerRecord> records;
    @OneToMany(cascade = {CascadeType.ALL})
    protected Set<PitanjeTimestamp> pitanjeRecords;

    public ReseniTest(){}

    public Long getId()
    {
        if (id != null)
            return id;
        else
            return 0l;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDatumVremePocetak() {
        return datumVremePocetak;
    }

    public void setDatumVremePocetak(Timestamp datum) {
        this.datumVremePocetak = datum;
    }

    public Timestamp getDatumVremeKraj() {
        return datumVremeKraj;
    }

    public void setDatumVremeKraj(Timestamp datum) {
        this.datumVremeKraj = datum;
    }

    public int getBrojOstvarenihBodova() {
        return brojOstvarenihBodova;
    }

    public void setBrojOstvarenihBodova(int brojOstvarenihBodova) {
        this.brojOstvarenihBodova = brojOstvarenihBodova;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public User getUcenik() {
        return ucenik;
    }

    public void setUcenik(User ucenik) {
        this.ucenik = ucenik;
    }

    public Set<Odgovor> getOdgovorSet() { return odgovorSet; }

    public void setOdgovorSet(Set<Odgovor> odgovorSet) { this.odgovorSet = odgovorSet; }

    public Set<EyetrackerRecord> getRecords() { return records; }

    public void setRecords(Set<EyetrackerRecord> records) { this.records = records; }

    public Set<PitanjeTimestamp> getPitanjeRecords() { return pitanjeRecords; }

    public void setPitanjeRecords(Set<PitanjeTimestamp> pitanjeRecords) { this.pitanjeRecords = pitanjeRecords; }
}
