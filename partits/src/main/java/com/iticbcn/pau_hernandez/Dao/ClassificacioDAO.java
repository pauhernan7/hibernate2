package com.iticbcn.pau_hernandez.Dao;

import org.hibernate.SessionFactory;

import com.iticbcn.pau_hernandez.Model.Classificacio;

public class ClassificacioDAO extends GenDAOImpl<Classificacio> {

    public ClassificacioDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Classificacio.class);
    }

    public long contarClassificacions() {
        return getSessionFactory().openSession()
                .createQuery("SELECT COUNT(c) FROM Classificacio c", Long.class)
                .getSingleResult();
    }
}