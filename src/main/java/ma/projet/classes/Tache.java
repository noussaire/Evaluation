package ma.projet.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Tache.prixSuperieurA1000", query = "from Tache t where t.prix > 1000")
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prix;

    @ManyToOne
    private Projet projet;

    @OneToMany(mappedBy = "tache")
    private List<EmployeTache> employeTaches = new ArrayList<>();

    public Tache() {
    }

    public Tache(String nom, LocalDate dateDebut, LocalDate dateFin, double prix, Projet projet) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.projet = projet;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public double getPrix() {
        return prix;
    }

    public Projet getProjet() {
        return projet;
    }
}
