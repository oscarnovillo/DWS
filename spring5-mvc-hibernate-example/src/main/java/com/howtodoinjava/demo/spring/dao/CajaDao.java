/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.demo.spring.dao;

import com.howtodoinjava.demo.spring.model.Caja;
import com.howtodoinjava.demo.spring.model.User;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author oscar
 */
@Repository
public class CajaDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Caja user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public List<Caja> list() {
        @SuppressWarnings("unchecked")
        TypedQuery<Caja> query = sessionFactory.getCurrentSession().createQuery("from Caja");
        return query.getResultList();
    }

}
