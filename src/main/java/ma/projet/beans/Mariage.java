package ma.projet.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Mariage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int nombreEnfants;

    @ManyToOne
    private Homme homme;

    @ManyToOne
    private Femme femme;

    public Mariage() {
    }

    public Mariage(Homme homme, Femme femme, LocalDate dateDebut, LocalDate dateFin, int nombreEnfants) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreEnfants = nombreEnfants;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public int getNombreEnfants() {
        return nombreEnfants;
    }

    public Homme getHomme() {
        return homme;
    }

    public Femme getFemme() {
        return femme;
    }
}
