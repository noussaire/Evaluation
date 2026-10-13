package ma.projet.test;

import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.service.EmployeService;
import ma.projet.service.EmployeTacheService;
import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;
import ma.projet.util.HibernateUtil;

import java.time.LocalDate;

public class TestExercice2 {
    public static void main(String[] args) {
        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();
        EmployeTacheService employeTacheService = new EmployeTacheService();

        Employe chef = new Employe("Safi", "Said");
        Employe developpeur = new Employe("Rami", "Salima");
        employeService.create(chef);
        employeService.create(developpeur);

        Projet projet = new Projet("Gestion de stock", LocalDate.of(2013, 1, 14), chef);
        projetService.create(projet);

        Tache analyse = new Tache("Analyse", LocalDate.of(2013, 2, 1), LocalDate.of(2013, 2, 28), 800, projet);
        Tache conception = new Tache("Conception", LocalDate.of(2013, 3, 1), LocalDate.of(2013, 3, 31), 1200, projet);
        Tache developpement = new Tache("Developpement", LocalDate.of(2013, 4, 1), LocalDate.of(2013, 4, 30), 5000, projet);
        tacheService.create(analyse);
        tacheService.create(conception);
        tacheService.create(developpement);

        employeTacheService.create(new EmployeTache(developpeur, analyse, LocalDate.of(2013, 2, 10), LocalDate.of(2013, 2, 20)));
        employeTacheService.create(new EmployeTache(developpeur, conception, LocalDate.of(2013, 3, 10), LocalDate.of(2013, 3, 15)));
        employeTacheService.create(new EmployeTache(developpeur, developpement, LocalDate.of(2013, 4, 10), LocalDate.of(2013, 4, 25)));

        System.out.println("Taches realisees par employe :");
        employeService.afficherTachesRealisees(developpeur);

        System.out.println("\nProjets geres par employe :");
        employeService.afficherProjetsGeres(chef);

        System.out.println("\nTaches planifiees :");
        projetService.afficherTachesPlanifiees(projet);

        System.out.println("\nTaches realisees avec dates reelles :");
        projetService.afficherTachesRealiseesAvecDates(projet);

        System.out.println("\nTaches dont le prix est superieur a 1000 DH :");
        tacheService.afficherTachesPrixSuperieurA1000();

        System.out.println("\nTaches realisees entre deux dates :");
        tacheService.afficherTachesRealiseesEntreDates(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 4, 30));

        HibernateUtil.shutdown();
    }
}
