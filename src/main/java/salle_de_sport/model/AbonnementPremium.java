package salle_de_sport.model;
import java.time.LocalDate;

public class AbonnementPremium extends Abonnement {
    private int creditsCoach;

    public AbonnementPremium(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel, int creditsCoach) {
        super(reference, dateDebut, dureeMois, prixMensuel);
        this.creditsCoach = creditsCoach;
    }

    public int getCreditsCoach() {
        return creditsCoach;
    }

    @Override
    public boolean permetAccesSauna() {
        return true;
    }

    @Override
    public int creditsCoachInclus() {
        return creditsCoach;
    }

    @Override
    public String toString() {
        return "AbonnementPremium{" +
                "reference='" + getReference() + '\'' +
                ", dateDebut=" + getDateDebut() +
                ", dureeMois=" + getDureeMois() +
                ", prixMensuel=" + getPrixMensuel() +
                ", dateFin=" + dateFin() +
                ", coutTotal=" + coutTotal() +
                ", creditsCoach=" + creditsCoach +
                ", accesSauna=" + permetAccesSauna() +
                '}';
    }
}
