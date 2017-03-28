
package com.deskind.tradeoptimization.entities;

import com.deskind.tradeoptimization.utils.LocalDateTimeAttributeConverter;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sgn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime date;
    
    @Column
    private int result;
    
    @Column
    private String pair;
    
    public Sgn() {
    }

    public Sgn(LocalDateTime date, int result) {
        this.date = date;
        this.result = result;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }
    
    

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getResult() {
        return result;
    }

    public String getPair() {
        return pair;
    }
    
    
}
