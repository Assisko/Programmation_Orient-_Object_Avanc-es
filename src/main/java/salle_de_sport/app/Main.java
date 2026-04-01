package salle_de_sport.app;

import salle_de_sport.AdherentDAO;
import salle_de_sport.exception.AdherentIntrouvableException;
import salle_de_sport.model.*;
import salle_de_sport.service.SalleDeSport;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        SalleDeSport salle = new SalleDeSport();

        // Prestations
        Prestation sauna = new Prestation("SAUNA", "Accès sauna", 5);
        Prestation coach = new Prestation("COACH", "Séance coach", 25);
        Prestation serviette = new Prestation("SERVIETTE", "Location serviette", 2);

        // Séances
        Seance s1 = new Seance(1, "BodyPump", LocalDateTime.now().plusDays(1), 20);
        Seance s2 = new Seance(2, "Yoga", LocalDateTime.now().plusDays(3), 15);
        Seance s3 = new Seance(3, "CrossFit", LocalDateTime.now().minusDays(2), 10);

        salle.ajouterSeance(s1);
        salle.ajouterSeance(s2);
        salle.ajouterSeance(s3);

        // Abonnements
        Abonnement basic = new AbonnementBasic("B001", LocalDate.now(), 6, 29.99);
        Abonnement premium = new AbonnementPremium("P001", LocalDate.now(), 12, 49.99, 5);

        // Adhérents
        Adherent a1 = new Adherent(1, "Alice", basic);
        Adherent a2 = new Adherent(2, "Karim", premium);

        salle.ajouterAdherent(a1);
        salle.ajouterAdherent(a2);

        // Réservations
        Reservation r1 = a1.reserver(s1);
        r1.ajouterPrestation(serviette);

        Reservation r2 = a1.reserver(s3);
        r2.ajouterPrestation(coach);
        r2.annuler();

        Reservation r3 = a2.reserver(s2);
        r3.ajouterPrestation(sauna);
        r3.ajouterPrestation(coach);

        Reservation r4 = a2.reserver(s1);
        r4.ajouterPrestation(serviette);

        // Affichages
        System.out.println("=== ADHERENTS + ABONNEMENTS ===");
        for (Adherent a : salle.getAdherents()) {
            System.out.println(a.getNom() + " -> " + a.getAbonnement());
        }

        System.out.println("\n=== RESERVATIONS FUTURES DE Alice ===");
        for (Reservation r : a1.reservationsFutures()) {
            System.out.println(r);
        }

        System.out.println("\n=== ADHERENTS AVEC ACCES SAUNA ===");
        for (Adherent a : salle.adherentsAvecSauna()) {
            System.out.println(a.getNom());
        }

        System.out.println("\n=== CHIFFRE D'AFFAIRES PRESTATIONS ===");
        System.out.println(salle.chiffreAffairesPrestations());

        System.out.println("\n=== TEST RECHERCHE ADHERENT ===");
        try {
            Adherent trouve = salle.trouverAdherent(2);
            System.out.println("Trouvé : " + trouve.getNom());
        } catch (AdherentIntrouvableException e) {
            System.out.println(e.getMessage());
        }

        // ===== PARTIE JDBC - DAO =====
        AdherentDAO dao = new AdherentDAO();

        // 1. Insérer deux adhérents
        dao.inserer(a1);  // Alice
        dao.inserer(a2);  // Karim

        // 2. Afficher la liste
        System.out.println("\n--- Liste des adhérents ---");
        for (Adherent a : dao.lister()) {
            System.out.println(a.getId() + " - " + a.getNom());
        }

        // 3. Modifier Karim → Robert
        System.out.println("\nModification de Karim...");
        dao.modifier(new Adherent(2, "Robert", null));

        // 4. Afficher après modification
        System.out.println("\n--- Liste après modification ---");
        for (Adherent a : dao.lister()) {
            System.out.println(a.getId() + " - " + a.getNom());
        }

        // 5. Supprimer Alice
        System.out.println("\nSuppression de Alice...");
        dao.supprimer(1);

        // 6. Liste finale
        System.out.println("\n--- Liste finale ---");
        for (Adherent a : dao.lister()) {
            System.out.println(a.getId() + " - " + a.getNom());
        }

    }
}