package salle_de_sport.model;
import java.time.LocalDate;

public class AbonnementBasic extends Abonnement {

    public AbonnementBasic(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel) {
        super(reference, dateDebut, dureeMois, prixMensuel);
    }

    @Override
    public boolean permetAccesSauna() {
        return false;
    }

    @Override
    public int creditsCoachInclus() {
        return 0;
    }

    @Override
    public String toString() {
        return "AbonnementBasic{" +
                "reference='" + getReference() + '\'' +
                ", dateDebut=" + getDateDebut() +
                ", dureeMois=" + getDureeMois() +
                ", prixMensuel=" + getPrixMensuel() +
                ", dateFin=" + dateFin() +
                ", coutTotal=" + coutTotal() +
                ", accesSauna=" + permetAccesSauna() +
                ", creditsCoachInclus=" + creditsCoachInclus() +
                '}';
    }
}
