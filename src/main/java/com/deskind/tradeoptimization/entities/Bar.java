
package com.deskind.tradeoptimization.entities;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime date;
    @Column
    private float openPrice;
    @Column
    private float maxPrice;
    @Column
    private float minPrice;
    @Column
    private float closePrise;
    @Column
    private int value;

    public Bar() {
    }

    public Bar(LocalDateTime date, float openPrice, float maxPrice, float minPrice, float closePrise, int value) {
        this.date = date;
        this.openPrice = openPrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.closePrise = closePrise;
        this.value = value;
    }
    
    

    public void setId(long id) {
        this.id = id;
    }
    
    

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setOpenPrice(float openPrice) {
        this.openPrice = openPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public void setClosePrise(float closePrise) {
        this.closePrise = closePrise;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }
    
    
    
    public LocalDateTime getDate() {
        return date;
    }

    public float getOpenPrice() {
        return openPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public float getClosePrise() {
        return closePrise;
    }

    public int getValue() {
        return value;
    }
    
    
    
}
