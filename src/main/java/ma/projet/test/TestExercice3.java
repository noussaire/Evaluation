package ma.projet.test;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.HibernateUtil;

import java.time.LocalDate;

public class TestExercice3 {
    public static void main(String[] args) {
        FemmeService femmeService = new FemmeService();
        HommeService hommeService = new HommeService();
        MariageService mariageService = new MariageService();

        Femme[] femmes = {
                new Femme("Salima", "Rami", LocalDate.of(1970, 5, 10)),
                new Femme("Amal", "Ali", LocalDate.of(1975, 8, 12)),
                new Femme("Wafa", "Alaoui", LocalDate.of(1980, 1, 20)),
                new Femme("Karima", "Alami", LocalDate.of(1968, 11, 3)),
                new Femme("Nadia", "Bennani", LocalDate.of(1985, 2, 14)),
                new Femme("Sara", "Idrissi", LocalDate.of(1990, 7, 7)),
                new Femme("Hind", "Fassi", LocalDate.of(1982, 4, 21)),
                new Femme("Imane", "Mansouri", LocalDate.of(1988, 9, 9)),
                new Femme("Leila", "Ziani", LocalDate.of(1978, 12, 30)),
                new Femme("Mina", "Omari", LocalDate.of(1992, 6, 1))
        };
        for (Femme femme : femmes) {
            femmeService.create(femme);
        }

        Homme[] hommes = {
                new Homme("Safi", "Said", LocalDate.of(1960, 1, 1)),
                new Homme("Rachid", "Nouri", LocalDate.of(1965, 3, 15)),
                new Homme("Youssef", "El Fadil", LocalDate.of(1972, 10, 5)),
                new Homme("Omar", "Tazi", LocalDate.of(1980, 6, 18)),
                new Homme("Mehdi", "Ait Ali", LocalDate.of(1984, 2, 8))
        };
        for (Homme homme : hommes) {
            hommeService.create(homme);
        }

        mariageService.create(new Mariage(hommes[0], femmes[3], LocalDate.of(1989, 9, 3), LocalDate.of(1990, 9, 3), 0));
        mariageService.create(new Mariage(hommes[0], femmes[0], LocalDate.of(1990, 9, 3), null, 4));
        mariageService.create(new Mariage(hommes[0], femmes[1], LocalDate.of(1995, 9, 3), null, 2));
        mariageService.create(new Mariage(hommes[0], femmes[2], LocalDate.of(2000, 11, 4), null, 3));
        mariageService.create(new Mariage(hommes[0], femmes[4], LocalDate.of(2005, 1, 1), null, 1));
        mariageService.create(new Mariage(hommes[1], femmes[0], LocalDate.of(1988, 1, 1), LocalDate.of(1989, 1, 1), 1));

        System.out.println("Liste des femmes :");
        femmeService.findAll().forEach(f ->
                System.out.printf("%d %s %s%n", f.getId(), f.getNom(), f.getPrenom()));

        Femme plusAgee = femmeService.findPlusAgee();
        System.out.printf("%nFemme la plus agee : %s %s%n", plusAgee.getNom(), plusAgee.getPrenom());

        System.out.println("\nEpouses d'un homme entre deux dates :");
        hommeService.afficherEpousesEntreDates(hommes[0], LocalDate.of(1989, 1, 1), LocalDate.of(2001, 1, 1));

        int enfants = femmeService.nombreEnfantsEntreDates(
                femmes[0], LocalDate.of(1980, 1, 1), LocalDate.of(2000, 1, 1));
        System.out.printf("%nNombre d'enfants de %s entre deux dates : %d%n", femmes[0].getNom(), enfants);

        System.out.println("\nFemmes mariees deux fois ou plus :");
        femmeService.findMarieesDeuxFoisOuPlus().forEach(f ->
                System.out.printf("%s %s%n", f.getNom(), f.getPrenom()));

        long hommesAvecQuatreFemmes = mariageService.nombreHommesMariesAQuatreFemmesEntreDates(
                LocalDate.of(1989, 1, 1), LocalDate.of(2010, 1, 1));
        System.out.printf("%nHommes maries a quatre femmes entre deux dates : %d%n", hommesAvecQuatreFemmes);

        System.out.println("\nMariages d'un homme :");
        hommeService.afficherMariages(hommes[0]);

        HibernateUtil.shutdown();
    }
}
