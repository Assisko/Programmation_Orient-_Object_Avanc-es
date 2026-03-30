package salle_de_sport.model;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private Seance seance;
    private List<Prestation> prestations;
    private StatutReservation statut;

    public Reservation(Seance seance) {
        this.seance = seance;
        this.prestations = new ArrayList<>();
        this.statut = StatutReservation.CONFIRMEE;
    }

    public Seance getSeance() {
        return seance;
    }

    public List<Prestation> getPrestations() {
        return prestations;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void ajouterPrestation(Prestation p) {
        prestations.add(p);
    }

    public double coutPrestations() {
        double somme = 0;
        for (Prestation p : prestations) {
            somme += p.getPrix();
        }
        return somme;
    }

    public void annuler() {
        statut = StatutReservation.ANNULEE;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "seance=" + seance +
                ", prestations=" + prestations +
                ", statut=" + statut +
                ", coutPrestations=" + coutPrestations() +
                '}';
    }
}