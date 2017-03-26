/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deskind.tradeoptimization.entities;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TRADE_PAIRS")
public class TradePair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PAIR_ID")
    private long id;
    
    @Column(name="NAME")
    private String name;
    
    @OneToMany(targetEntity = Sgn.class, cascade = CascadeType.ALL)
    @Column(name="SIGNALS")
    private List<Sgn> list;

    public TradePair() {
    }

    public TradePair(String name, List<Sgn> list) {
        this.name = name;
        this.list = list;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List<Sgn> list) {
        this.list = list;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Sgn> getList() {
        return list;
    }
    
    
}
