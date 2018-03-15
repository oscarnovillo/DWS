/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.demo.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author oscar
 */
@Entity
@Table(name = "TBL_CAJAS")
public class Caja {

    @Id
    @GeneratedValue
    @Column(name = "CAJA_ID")
    private Long id;

    
    @Column(name = "CAJA_NAME")
    @Size(max = 20, min = 3, message = "{user.name.invalid}")
    @NotEmpty(message = "Please Enter your name")
    private String name;

    public Caja() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Caja(Long id,  String name) {
        this.id = id;
       
        this.name = name;
    }

}
