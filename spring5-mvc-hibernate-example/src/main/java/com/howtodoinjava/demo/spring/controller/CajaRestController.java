/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.demo.spring.controller;

import com.howtodoinjava.demo.spring.model.Caja;
import com.howtodoinjava.demo.spring.service.CajaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/cajitas")
public class CajaRestController {
    
    
    @Autowired
    private CajaService userService;

    @GetMapping
    public List<Caja> list() {
        return userService.list();
    }
    
    
}
