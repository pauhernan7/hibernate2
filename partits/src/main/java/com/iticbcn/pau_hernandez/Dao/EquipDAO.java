package com.iticbcn.pau_hernandez.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.iticbcn.pau_hernandez.Model.Equip;
import com.iticbcn.pau_hernandez.Model.Lliga;

public class EquipDAO extends GenDAOImpl<Equip> {

    public EquipDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Equip.class);
    }

    public void crearEquip(Equip equip, int idLliga) throws Exception {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            // Verificar si la liga existe
            Lliga lliga = session.get(Lliga.class, idLliga);
            if (lliga == null) {
                throw new Exception("La liga con ID " + idLliga + " no existe.");
            }

            equip.setLliga(lliga); // Asocia el equipo con la liga
            save(equip); // Guarda el equipo en la base de datos

            session.getTransaction().commit();
            System.out.println("✅ Equip creat correctament!");
        }
    }

    public long contarEquips() {
        return getSessionFactory().openSession()
                .createQuery("SELECT COUNT(e) FROM Equip e", Long.class)
                .getSingleResult();
    }
}