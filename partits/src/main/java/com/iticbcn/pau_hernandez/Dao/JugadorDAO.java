package com.iticbcn.pau_hernandez.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.iticbcn.pau_hernandez.Model.Equip;
import com.iticbcn.pau_hernandez.Model.Jugador;

public class JugadorDAO extends GenDAOImpl<Jugador> {

    public JugadorDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Jugador.class);
    }

    public void crearJugador(Jugador jugador, int idEquip) throws Exception {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            // Verificar si el equipo existe
            Equip equip = session.get(Equip.class, idEquip);
            if (equip == null) {
                throw new Exception("El equipo con ID " + idEquip + " no existe.");
            }

            jugador.setEquip(equip); // Asocia el jugador al equipo
            save(jugador); // Guarda el jugador en la base de datos

            session.getTransaction().commit();
            System.out.println("✅ Jugador creat correctament!");
        }
    }

    public long contarJugadors() {
        return getSessionFactory().openSession()
                .createQuery("SELECT COUNT(j) FROM Jugador j", Long.class)
                .getSingleResult();
    }
}