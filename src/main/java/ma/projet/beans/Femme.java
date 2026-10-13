package ma.projet.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(
        name = "Femme.marieesDeuxFoisOuPlus",
        query = "select f from Femme f where size(f.mariages) >= 2"
)
@NamedNativeQuery(
        name = "Femme.nombreEnfantsEntreDates",
        query = "select coalesce(sum(m.nombreEnfants), 0) from Mariage m "
                + "where m.femme_id = :femmeId and m.dateDebut between :debut and :fin"
)
public class Femme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "femme")
    private List<Mariage> mariages = new ArrayList<>();

    public Femme() {
    }

    public Femme(String nom, String prenom, LocalDate dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
}
