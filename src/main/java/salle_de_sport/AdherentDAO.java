package salle_de_sport;

import salle_de_sport.model.Adherent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdherentDAO {

    // INSERT — Insérer un adhérent
    public void inserer(Adherent adherent) {
        String sql = "INSERT INTO adherent (id, nom) VALUES (?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, adherent.getId());
            pst.setString(2, adherent.getNom());
            pst.executeUpdate();
            pst.close();
            conn.close();
            System.out.println("✔ Adhérent inséré : " + adherent.getNom());
        } catch (SQLException e) {
            System.err.println("Erreur insertion : " + e.getMessage());
        }
    }

    // SELECT — Lister tous les adhérents
    public List<Adherent> lister() {
        List<Adherent> liste = new ArrayList<>();
        String sql = "SELECT * FROM adherent";
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                liste.add(new Adherent(rs.getInt("id"), rs.getString("nom"), null));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Erreur liste : " + e.getMessage());
        }
        return liste;
    }

    // UPDATE — Modifier le nom d'un adhérent
    public void modifier(Adherent adherent) {
        String sql = "UPDATE adherent SET nom = ? WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, adherent.getNom());
            pst.setInt(2, adherent.getId());
            pst.executeUpdate();
            pst.close();
            conn.close();
            System.out.println("✔ Adhérent modifié : " + adherent.getNom());
        } catch (SQLException e) {
            System.err.println("Erreur modification : " + e.getMessage());
        }
    }

    // DELETE — Supprimer un adhérent
    public void supprimer(int id) {
        String sql = "DELETE FROM adherent WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            conn.close();
            System.out.println("✔ Adhérent supprimé (id=" + id + ")");
        } catch (SQLException e) {
            System.err.println("Erreur suppression : " + e.getMessage());
        }
    }

    // SELECT WHERE — Trouver par ID
    public Adherent trouverParId(int id) {
        String sql = "SELECT * FROM adherent WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Adherent(rs.getInt("id"), rs.getString("nom"), null);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Erreur recherche : " + e.getMessage());
        }
        return null;
    }
}