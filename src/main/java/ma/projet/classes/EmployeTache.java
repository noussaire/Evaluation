package ma.projet.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class EmployeTache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateDebutReelle;
    private LocalDate dateFinReelle;

    @ManyToOne
    private Employe employe;

    @ManyToOne
    private Tache tache;

    public EmployeTache() {
    }

    public EmployeTache(Employe employe, Tache tache, LocalDate dateDebutReelle, LocalDate dateFinReelle) {
        this.employe = employe;
        this.tache = tache;
        this.dateDebutReelle = dateDebutReelle;
        this.dateFinReelle = dateFinReelle;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateDebutReelle() {
        return dateDebutReelle;
    }

    public LocalDate getDateFinReelle() {
        return dateFinReelle;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Tache getTache() {
        return tache;
    }
}
