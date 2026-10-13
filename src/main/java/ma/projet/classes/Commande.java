package ma.projet.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;

    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> lignes = new ArrayList<>();

    public Commande() {
    }

    public Commande(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }
}
