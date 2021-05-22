package com.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class PitanjeTimestamp
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    Timestamp timestampStart;
    @Column
    Timestamp timestampEnd;
    @ManyToOne
    protected Pitanje pitanje;

    public PitanjeTimestamp(){}

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Timestamp getTimestampStart()
    {
        return timestampStart;
    }

    public void setTimestampStart(Timestamp timestampStart)
    {
        this.timestampStart = timestampStart;
    }

    public Timestamp getTimestampEnd()
    {
        return timestampEnd;
    }

    public void setTimestampEnd(Timestamp timestampEnd)
    {
        this.timestampEnd = timestampEnd;
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
