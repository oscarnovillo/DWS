/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.demo.spring.service;

import com.howtodoinjava.demo.spring.dao.CajaDao;
import com.howtodoinjava.demo.spring.dao.UserDao;
import com.howtodoinjava.demo.spring.model.Caja;
import com.howtodoinjava.demo.spring.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author oscar
 */
@Service
public class CajaService {
    @Autowired
   private CajaDao userDao;

   @Transactional
   public void save(Caja user) {
      userDao.save(user);
   }

   @Transactional(readOnly = true)
   public List<Caja> list() {
      return userDao.list();
   }
}
