package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HommeService extends AbstractService<Homme> {
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public HommeService() {
        super(Homme.class);
    }

    public List<Femme> findEpousesEntreDates(Homme homme, LocalDate debut, LocalDate fin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select distinct m.femme from Mariage m "
                                    + "where m.homme = :homme and m.dateDebut between :debut and :fin",
                            Femme.class)
                    .setParameter("homme", homme)
                    .setParameter("debut", debut)
                    .setParameter("fin", fin)
                    .list();
        }
    }

    public List<Mariage> findMariages(Homme homme) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select m from Mariage m join fetch m.femme where m.homme = :homme "
                                    + "order by m.dateFin asc, m.dateDebut asc",
                            Mariage.class)
                    .setParameter("homme", homme)
                    .list();
        }
    }

    public void afficherEpousesEntreDates(Homme homme, LocalDate debut, LocalDate fin) {
        findEpousesEntreDates(homme, debut, fin).forEach(f ->
                System.out.printf("%s %s%n", f.getNom(), f.getPrenom()));
    }

    public void afficherMariages(Homme homme) {
        System.out.printf("Nom : %s %s%n", homme.getNom(), homme.getPrenom());
        System.out.println("Mariages En Cours :");
        int index = 1;
        for (Mariage mariage : findMariages(homme)) {
            if (mariage.getDateFin() == null) {
                afficherMariage(index++, mariage, false);
            }
        }

        System.out.println();
        System.out.println("Mariages echoues :");
        index = 1;
        for (Mariage mariage : findMariages(homme)) {
            if (mariage.getDateFin() != null) {
                afficherMariage(index++, mariage, true);
            }
        }
    }

    private void afficherMariage(int index, Mariage mariage, boolean avecDateFin) {
        Femme femme = mariage.getFemme();
        System.out.printf("%d. Femme : %s %s   Date Debut : %s",
                index, femme.getNom(), femme.getPrenom(), mariage.getDateDebut().format(DATE));
        if (avecDateFin) {
            System.out.printf("   Date Fin : %s", mariage.getDateFin().format(DATE));
        }
        System.out.printf("   Nbr Enfants : %d%n", mariage.getNombreEnfants());
    }
}
