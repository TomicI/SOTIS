package com.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class EyetrackerRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)
    protected Timestamp timestamp;
    @Column(nullable = false)
    protected Double leftEyeX;
    @Column(nullable = false)
    protected Double leftEyeY;
    @Column(nullable = false)
    protected Double rightEyeX;
    @Column(nullable = false)
    protected Double rightEyeY;


    public EyetrackerRecord() { }
/*
    public EyetrackerRecord(Timestamp timestamp, Double leftEyeX, Double leftEyeY, Double rightEyeX, Double rightEyeY) {
        this.timestamp = timestamp;
        this.leftEyeX = leftEyeX;
        this.leftEyeY = leftEyeY;
        this.rightEyeX = rightEyeX;
        this.rightEyeY = rightEyeY;
    }
*/
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Timestamp getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp)
    {
        this.timestamp = timestamp;
    }

    public Double getLeftEyeX()
    {
        return leftEyeX;
    }

    public void setLeftEyeX(Double leftEyeX)
    {
        this.leftEyeX = leftEyeX;
    }

    public Double getLeftEyeY()
    {
        return leftEyeY;
    }

    public void setLeftEyeY(Double leftEyeY)
    {
        this.leftEyeY = leftEyeY;
    }

    public Double getRightEyeX()
    {
        return rightEyeX;
    }

    public void setRightEyeX(Double rightEyeX)
    {
        this.rightEyeX = rightEyeX;
    }

    public Double getRightEyeY()
    {
        return rightEyeY;
    }

    public void setRightEyeY(Double rightEyeY)
    {
        this.rightEyeY = rightEyeY;
    }


    public void setAll(Timestamp timestamp, Double leftEyeX, Double leftEyeY, Double rightEyeX, Double rightEyeY)
    {
        this.timestamp = timestamp;
        this.leftEyeX = leftEyeX;
        this.leftEyeY = leftEyeY;
        this.rightEyeX = rightEyeX;
        this.rightEyeY = rightEyeY;
    }
}
