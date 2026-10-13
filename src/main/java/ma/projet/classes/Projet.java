package ma.projet.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private LocalDate dateDebut;

    @ManyToOne
    private Employe chef;

    @OneToMany(mappedBy = "projet")
    private List<Tache> taches = new ArrayList<>();

    public Projet() {
    }

    public Projet(String nom, LocalDate dateDebut, Employe chef) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.chef = chef;
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

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Employe getChef() {
        return chef;
    }

    public void setChef(Employe chef) {
        this.chef = chef;
    }
}
