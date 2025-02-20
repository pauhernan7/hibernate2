package com.iticbcn.pau_hernandez.Dao;

import org.hibernate.SessionFactory;

import com.iticbcn.pau_hernandez.Model.Lliga;

public class LligaDAO extends GenDAOImpl<Lliga> {

    public LligaDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Lliga.class);
    }

    public boolean existeLliga(int id) throws Exception {
        return get(id) != null;
    }

    public long contarLligues() {
        return getSessionFactory().openSession()
                .createQuery("SELECT COUNT(l) FROM Lliga l", Long.class)
                .getSingleResult();
    }
}