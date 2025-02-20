package com.iticbcn.pau_hernandez;

import org.hibernate.SessionFactory;

import com.iticbcn.pau_hernandez.Dao.ClassificacioDAO;
import com.iticbcn.pau_hernandez.Dao.EquipDAO;
import com.iticbcn.pau_hernandez.Dao.JugadorDAO;
import com.iticbcn.pau_hernandez.Dao.LligaDAO;
import com.iticbcn.pau_hernandez.Model.Classificacio;
import com.iticbcn.pau_hernandez.Model.Equip;
import com.iticbcn.pau_hernandez.Model.Jugador;
import com.iticbcn.pau_hernandez.Model.Lliga;

public class Main {

    public static void mostrarOpcions() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1. Gestionar Lliga");
        System.out.println("2. Gestionar Jugadors");
        System.out.println("3. Gestionar Classificació");
        System.out.println("4. Gestionar Equips");
        System.out.println("5. Sortir");
        System.out.print("Selecciona una opció: ");
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EquipDAO equipDAO = new EquipDAO(sessionFactory);
        LligaDAO lligaDAO = new LligaDAO(sessionFactory);
        ClassificacioDAO classificacioDAO = new ClassificacioDAO(sessionFactory);
        JugadorDAO jugadorDAO = new JugadorDAO(sessionFactory);

        boolean sortir = false;

        while (!sortir) {
            mostrarOpcions();
            int opcio = Integer.parseInt(Entrada.readLine());

            switch (opcio) {
                case 1:
                    gestionarLliga(lligaDAO);
                    break;
                case 2:
                    gestionarJugador(jugadorDAO, equipDAO);
                    break;
                case 3:
                    gestionarClassificacio(classificacioDAO, equipDAO);
                    break;
                case 4:
                    gestionarEquip(equipDAO, lligaDAO);
                    break;
                case 5:
                    sortir = true;
                    System.out.println("Sortint de l'aplicació. Fins aviat!");
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }
        }

        sessionFactory.close();
    }

    // Submenú per gestionar Lliga
    public static void gestionarLliga(LligaDAO lligaDAO) {
        System.out.println("\n===== GESTIONAR LLIGA =====");
        System.out.println("1. Crear Lliga");
        System.out.println("2. Consultar Lliga per ID");
        System.out.println("3. Actualitzar Lliga per ID");
        System.out.println("4. Eliminar Lliga per ID");
        System.out.println("5. Llistar totes les Lligues");
        System.out.println("6. Count de totes les Lligues");
        System.out.print("Selecciona una opció: ");
    
        int opcio = Integer.parseInt(Entrada.readLine());
    
        try {
            switch (opcio) {
                case 1:
                    System.out.print("Nom de la lliga: ");
                    String nomLliga = Entrada.readLine();
                    System.out.print("Temporada: ");
                    String temporada = Entrada.readLine();
                    Lliga lliga = new Lliga();
                    lliga.setNom_lliga(nomLliga);
                    lliga.setTemporada(temporada);
                    lligaDAO.save(lliga); // Usamos el método genérico save
                    System.out.println("Lliga creada amb èxit!");
                    break;
    
                case 2:
                    System.out.print("Introdueix l'ID de la lliga a consultar: ");
                    int idConsulta = Integer.parseInt(Entrada.readLine());
                    Lliga l = lligaDAO.get(idConsulta); // Usamos el método genérico get
                    if (l != null) {
                        System.out.println("🏆 " + l.getNom_lliga() + " - " + l.getTemporada());
                    } else {
                        System.out.println("No s'ha trobat cap lliga amb aquest ID.");
                    }
                    break;
    
                case 3:
                    System.out.print("Introdueix l'ID de la lliga a actualitzar: ");
                    int idUpdate = Integer.parseInt(Entrada.readLine());
                    Lliga lligaUpdate = lligaDAO.get(idUpdate); // Usamos el método genérico get
                    if (lligaUpdate != null) {
                        System.out.print("Nou nom de la lliga: ");
                        lligaUpdate.setNom_lliga(Entrada.readLine());
                        System.out.print("Nova temporada: ");
                        lligaUpdate.setTemporada(Entrada.readLine());
                        lligaDAO.update(lligaUpdate); // Usamos el método genérico update
                        System.out.println("Lliga actualitzada amb èxit!");
                    } else {
                        System.out.println("No s'ha trobat cap lliga amb aquest ID.");
                    }
                    break;
    
                case 4:
                    System.out.print("Introdueix l'ID de la lliga a eliminar: ");
                    int idDelete = Integer.parseInt(Entrada.readLine());
                    Lliga lligaDelete = lligaDAO.get(idDelete); // Usamos el método genérico get
                    if (lligaDelete != null) {
                        lligaDAO.delete(lligaDelete); // Usamos el método genérico delete
                        System.out.println("Lliga eliminada amb èxit!");
                    } else {
                        System.out.println("No s'ha trobat cap lliga amb aquest ID.");
                    }
                    break;
    
                case 5:
                    System.out.println("\nLlistat de Lligues:");
                    for (Lliga ll : lligaDAO.getAll()) { // Usamos el método genérico getAll
                        System.out.println("Id lliga: " + ll.getId_lliga() + "    Nom Lliga: " + ll.getNom_lliga() + "    Temporada: " + ll.getTemporada());
                    }
                    break;
    
                case 6:
                    long countLligues = lligaDAO.getAll().size(); // Usamos el método genérico getAll
                    System.out.println("Número total de lligues: " + countLligues);
                    break;
    
                default:
                    System.out.println("Opció no vàlida.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Submenú per gestionar Jugador
    public static void gestionarJugador(JugadorDAO jugadorDAO, EquipDAO equipDAO) {
        System.out.println("\n===== GESTIONAR JUGADOR =====");
        System.out.println("1. Crear Jugador");
        System.out.println("2. Consultar Jugador per ID");
        System.out.println("3. Actualitzar Jugador per ID");
        System.out.println("4. Eliminar Jugador per ID");
        System.out.println("5. Llistar tots els Jugadors");
        System.out.println("6. Count de tots els Jugadors");
        System.out.print("Selecciona una opció: ");
        int opcio = Integer.parseInt(Entrada.readLine());
    
        try {
            switch (opcio) {
                case 1:
                    System.out.print("Nom del jugador: ");
                    String nomJugador = Entrada.readLine();
                    System.out.print("Cognoms: ");
                    String cognoms = Entrada.readLine();
    
                    System.out.print("Introdueix l'ID de l'equip al qual pertany el jugador: ");
                    int idEquip = Integer.parseInt(Entrada.readLine());
    
                    Equip equip = equipDAO.get(idEquip); // Usamos el método genérico get
                    if (equip == null) {
                        System.out.println("No s'ha trobat cap equip amb aquest ID. No es pot crear el jugador.");
                        return;
                    }
    
                    Jugador jugador = new Jugador();
                    jugador.setNom(nomJugador);
                    jugador.setCognoms(cognoms);
                    jugador.setEquip(equip);
    
                    jugadorDAO.save(jugador); // Usamos el método genérico save
                    System.out.println("Jugador creat amb èxit!");
                    break;
    
                case 2:
                    System.out.print("Introdueix l'ID del jugador a consultar: ");
                    int idConsulta = Integer.parseInt(Entrada.readLine());
                    Jugador j = jugadorDAO.get(idConsulta); // Usamos el método genérico get
                    if (j != null) {
                        System.out.println("Nom Jugador: " + j.getNom() + "    Cognoms: " + j.getCognoms());
                    } else {
                        System.out.println("No s'ha trobat cap jugador amb aquest ID.");
                    }
                    break;
    
                case 3:
                    System.out.print("Introdueix l'ID del jugador a actualitzar: ");
                    int idUpdate = Integer.parseInt(Entrada.readLine());
                    Jugador jugadorUpdate = jugadorDAO.get(idUpdate); // Usamos el método genérico get
                    if (jugadorUpdate != null) {
                        System.out.print("Nou nom del jugador: ");
                        jugadorUpdate.setNom(Entrada.readLine());
                        System.out.print("Nous cognoms del jugador: ");
                        jugadorUpdate.setCognoms(Entrada.readLine());
                        jugadorDAO.update(jugadorUpdate); // Usamos el método genérico update
                        System.out.println("Jugador actualitzat amb èxit!");
                    } else {
                        System.out.println("No s'ha trobat cap jugador amb aquest ID.");
                    }
                    break;
    
                case 4:
                    System.out.print("Introdueix l'ID del jugador a eliminar: ");
                    int idDelete = Integer.parseInt(Entrada.readLine());
                    Jugador jugadorDelete = jugadorDAO.get(idDelete); // Usamos el método genérico get
                    if (jugadorDelete != null) {
                        jugadorDAO.delete(jugadorDelete); // Usamos el método genérico delete
                        System.out.println("Jugador eliminat amb èxit!");
                    } else {
                        System.out.println("No s'ha trobat cap jugador amb aquest ID.");
                    }
                    break;
    
                case 5:
                    System.out.println("\nLlistat de Jugadors:");
                    for (Jugador juga : jugadorDAO.getAll()) { // Usamos el método genérico getAll
                        System.out.println("Id jugador: " + juga.getIdJugador() + "    Nom: " + juga.getNom() + "    Cognoms: " + juga.getCognoms() + "    Nom Equip: " + juga.getEquip().getNom_equip());
                    }
                    break;
    
                case 6:
                    long countJugadors = jugadorDAO.getAll().size(); // Usamos el método genérico getAll
                    System.out.println("Número total de jugadors: " + countJugadors);
                    break;
    
                default:
                    System.out.println("Opció no vàlida.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Submenú per gestionar Classificació
    public static void gestionarClassificacio(ClassificacioDAO classificacioDAO, EquipDAO equipDAO) {
        System.out.println("\n===== GESTIONAR CLASSIFICACIÓ =====");
        System.out.println("1. Crear Classificació");
        System.out.println("2. Consultar Classificació per ID");
        System.out.println("3. Actualitzar Classificació per ID");
        System.out.println("4. Eliminar Classificació per ID");
        System.out.println("5. Llistar totes les Classificacions");
        System.out.println("6. Count de totes les Classificacions");
        System.out.print("Selecciona una opció: ");
        int opcio = Integer.parseInt(Entrada.readLine());

        try {
            switch (opcio) {
                case 1:
                    System.out.print("Introdueix els punts: ");
                    int punts = Integer.parseInt(Entrada.readLine());
                    System.out.print("Introdueix partits jugats: ");
                    int partits = Integer.parseInt(Entrada.readLine());
                    System.out.print("Introdueix victòries: ");
                    int victories = Integer.parseInt(Entrada.readLine());

                    System.out.print("Introdueix l'ID de l'equip per associar la classificació: ");
                    int idEquip = Integer.parseInt(Entrada.readLine());

                    Equip equip = equipDAO.get(idEquip); // Usamos el método genérico get
                    if (equip == null) {
                        System.out.println("No s'ha trobat cap equip amb aquest ID. No es pot crear la classificació.");
                        return;
                    }

                    Classificacio classificacio = new Classificacio();
                    classificacio.setPunts(punts);
                    classificacio.setPartits_jugats(partits);
                    classificacio.setVictories(victories);
                    classificacio.setEquip(equip);

                    classificacioDAO.save(classificacio); // Usamos el método genérico save
                    System.out.println("Classificació creada amb èxit!");
                    break;

                case 2:
                    System.out.print("Introdueix l'ID de la classificació a consultar: ");
                    int idConsulta = Integer.parseInt(Entrada.readLine());
                    Classificacio c = classificacioDAO.get(idConsulta); // Usamos el método genérico get
                    if (c != null) {
                        System.out.println("Punts: " + c.getPunts() + " | Partits jugats: " + c.getPartits_jugats() + " | Victòries: " + c.getVictories());
                    } else {
                        System.out.println("No s'ha trobat cap classificació amb aquest ID.");
                    }
                    break;

                case 3:
                    System.out.print("Introdueix l'ID de la classificació a actualitzar: ");
                    int idUpdate = Integer.parseInt(Entrada.readLine());
                    Classificacio classificacioUpdate = classificacioDAO.get(idUpdate); // Usamos el método genérico get
                    if (classificacioUpdate != null) {
                        System.out.print("Nous punts: ");
                        classificacioUpdate.setPunts(Integer.parseInt(Entrada.readLine()));
                        System.out.print("Noves partits jugats: ");
                        classificacioUpdate.setPartits_jugats(Integer.parseInt(Entrada.readLine()));
                        System.out.print("Noves victòries: ");
                        classificacioUpdate.setVictories(Integer.parseInt(Entrada.readLine()));

                        classificacioDAO.update(classificacioUpdate); // Usamos el método genérico update
                        System.out.println("Classificació actualitzada amb èxit!");
                    } else {
                        System.out.println("No s'ha trobat cap classificació amb aquest ID.");
                    }
                    break;

                case 4:
                    System.out.print("Introdueix l'ID de la classificació a eliminar: ");
                    int idDelete = Integer.parseInt(Entrada.readLine());
                    Classificacio classificacioDelete = classificacioDAO.get(idDelete); // Usamos el método genérico get
                    if (classificacioDelete != null) {
                        classificacioDAO.delete(classificacioDelete); // Usamos el método genérico delete
                        System.out.println("Classificació eliminada amb èxit!");
                    } else {
                        System.out.println("No s'ha trobat cap classificació amb aquest ID.");
                    }
                    break;

                case 5:
                    System.out.println("\nLlistat de Classificacions:");
                    for (Classificacio clas : classificacioDAO.getAll()) { // Usamos el método genérico getAll
                        System.out.println("Id classificacio: " + clas.getId_classificacio() + "    Nom Equip: " + clas.getEquip().getNom_equip() + "    Punts: " + clas.getPunts() + "    Partits jugats: " + clas.getPartits_jugats() + "    Victories: " + clas.getVictories() + ")");
                    }
                    break;

                case 6:
                    long countClassificacions = classificacioDAO.getAll().size(); // Usamos el método genérico getAll
                    System.out.println("Número total de classificacions: " + countClassificacions);
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Submenú per gestionar Equip
    public static void gestionarEquip(EquipDAO equipDAO, LligaDAO lligaDAO) {
        System.out.println("\n===== GESTIONAR EQUIP =====");
        System.out.println("1. Crear Equip");
        System.out.println("2. Consultar Equip per ID");
        System.out.println("3. Actualitzar Equip per ID");
        System.out.println("4. Eliminar Equip per ID");
        System.out.println("5. Llistar tots els Equips");
        System.out.println("6. Count de tots els Equips");
        System.out.print("Selecciona una opció: ");
        int opcio = Integer.parseInt(Entrada.readLine());

        try {
            switch (opcio) {
                case 1:
                    System.out.print("Nom de l'equip: ");
                    String nomEquip = Entrada.readLine();
                    System.out.print("Ciutat de l'equip: ");
                    String ciutat = Entrada.readLine();

                    System.out.print("Introdueix l'ID de la lliga a la qual pertany l'equip: ");
                    int idLliga = Integer.parseInt(Entrada.readLine());

                    Lliga lliga = lligaDAO.get(idLliga); // Usamos el método genérico get
                    if (lliga == null) {
                        System.out.println("No s'ha trobat cap lliga amb aquest ID. No es pot crear l'equip.");
                        return;
                    }

                    Equip equip = new Equip();
                    equip.setNom_equip(nomEquip);
                    equip.setCiutat(ciutat);
                    equip.setLliga(lliga);

                    equipDAO.save(equip); // Usamos el método genérico save
                    System.out.println("Equip creat amb èxit!");
                    break;

                case 2:
                    System.out.print("Introdueix l'ID de l'equip a consultar: ");
                    int idConsulta = Integer.parseInt(Entrada.readLine());
                    Equip e = equipDAO.get(idConsulta); // Usamos el método genérico get
                    if (e != null) {
                        System.out.println(e.getNom_equip() + " - " + e.getCiutat() + " (Lliga: " + e.getLliga().getNom_lliga() + ")");
                    } else {
                        System.out.println("No s'ha trobat cap equip amb aquest ID.");
                    }
                    break;

                case 3:
                    System.out.print("Introdueix l'ID de l'equip a actualitzar: ");
                    int idUpdate = Integer.parseInt(Entrada.readLine());
                    Equip equipUpdate = equipDAO.get(idUpdate); // Usamos el método genérico get
                    if (equipUpdate != null) {
                        System.out.print("Nou nom de l'equip: ");
                        equipUpdate.setNom_equip(Entrada.readLine());
                        System.out.print("Nova ciutat de l'equip: ");
                        equipUpdate.setCiutat(Entrada.readLine());

                        equipDAO.update(equipUpdate); // Usamos el método genérico update
                        System.out.println("Equip actualitzat amb èxit!");
                    } else {
                        System.out.println("No s'ha trobat cap equip amb aquest ID.");
                    }
                    break;

                case 4:
                    System.out.print("Introdueix l'ID de l'equip a eliminar: ");
                    int idDelete = Integer.parseInt(Entrada.readLine());
                    Equip equipDelete = equipDAO.get(idDelete); // Usamos el método genérico get
                    if (equipDelete != null) {
                        equipDAO.delete(equipDelete); // Usamos el método genérico delete
                        System.out.println("Equip eliminat amb èxit!");
                    } else {
                        System.out.println("No s'ha trobat cap equip amb aquest ID.");
                    }
                    break;

                case 5:
                    System.out.println("\nLlistat d'Equips:");
                    for (Equip equipo : equipDAO.getAll()) { // Usamos el método genérico getAll
                        System.out.println("Id equip: " + equipo.getId_equip() + "    Nom Equip: " + equipo.getNom_equip() + "    Ciutat: " + equipo.getCiutat());
                    }
                    break;

                case 6:
                    long countEquips = equipDAO.getAll().size(); // Usamos el método genérico getAll
                    System.out.println("Número total d'equips: " + countEquips);
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        }
        
}