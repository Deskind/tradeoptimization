
package com.deskind.tradeoptimization.entities;

import com.deskind.tradeoptimization.utils.LocalDateTimeAttributeConverter;
import java.time.LocalDateTime;
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
    
    private int result;

    private String pair;
    
    public Sgn() {
    }

    public Sgn(LocalDateTime date, int openPrice) {
        this.date = date;
        this.result = openPrice;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setOpenPrice(int openPrice) {
        this.result = openPrice;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getOpenPrice() {
        return result;
    }
    
    
}
