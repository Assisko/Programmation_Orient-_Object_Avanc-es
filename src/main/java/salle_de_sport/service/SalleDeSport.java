package salle_de_sport.service;

import salle_de_sport.exception.AdherentIntrouvableException;
import salle_de_sport.model.Adherent;
import salle_de_sport.model.Seance;

import java.util.ArrayList;
import java.util.List;

public class SalleDeSport {
    private List<Adherent> adherents;
    private List<Seance> seances;

    public SalleDeSport() {
        adherents = new ArrayList<>();
        seances = new ArrayList<>();
    }

    public List<Adherent> getAdherents() {
        return adherents;
    }

    public List<Seance> getSeances() {
        return seances;
    }

    public void ajouterAdherent(Adherent a) {
        adherents.add(a);
    }

    public void ajouterSeance(Seance s) {
        seances.add(s);
    }

    public List<Seance> seancesDisponibles() {
        return new ArrayList<>(seances);
    }

    public List<Adherent> adherentsAvecSauna() {
        List<Adherent> resultat = new ArrayList<>();
        for (Adherent a : adherents) {
            if (a.getAbonnement().permetAccesSauna()) {
                resultat.add(a);
            }
        }
        return resultat;
    }

    public double chiffreAffairesPrestations() {
        double somme = 0;
        for (Adherent a : adherents) {
            somme += a.depensesTotales();
        }
        return somme;
    }

    public Adherent trouverAdherent(int id) throws AdherentIntrouvableException {
        for (Adherent a : adherents) {
            if (a.getId() == id) {
                return a;
            }
        }
        throw new AdherentIntrouvableException("Adhérent introuvable avec l'id : " + id);
    }
}