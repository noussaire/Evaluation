package ma.projet.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "chef")
    private List<Projet> projetsGeres = new ArrayList<>();

    @OneToMany(mappedBy = "employe")
    private List<EmployeTache> employeTaches = new ArrayList<>();

    public Employe() {
    }

    public Employe(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
