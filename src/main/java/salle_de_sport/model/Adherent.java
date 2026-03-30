package salle_de_sport.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Adherent {
    private int id;
    private String nom;
    private Abonnement abonnement;
    private List<Reservation> reservations;

    public Adherent(int id, String nom, Abonnement abonnement) {
        this.id = id;
        this.nom = nom;
        this.abonnement = abonnement;
        this.reservations = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Reservation reserver(Seance s) {
        Reservation reservation = new Reservation(s);
        reservations.add(reservation);
        return reservation;
    }

    public double depensesTotales() {
        double somme = 0;
        for (Reservation r : reservations) {
            if (r.getStatut() == StatutReservation.CONFIRMEE) {
                somme += r.coutPrestations();
            }
        }
        return somme;
    }

    public List<Reservation> reservationsFutures() {
        List<Reservation> futures = new ArrayList<>();
        LocalDateTime maintenant = LocalDateTime.now();

        for (Reservation r : reservations) {
            if (r.getSeance().getDateHeure().isAfter(maintenant)) {
                futures.add(r);
            }
        }

        return futures;
    }

    @Override
    public String toString() {
        return "Adherent{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", abonnement=" + abonnement +
                ", reservations=" + reservations +
                '}';
    }
}
