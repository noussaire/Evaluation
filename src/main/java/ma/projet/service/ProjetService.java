package ma.projet.service;

import ma.projet.classes.EmployeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProjetService extends AbstractService<Projet> {
    private static final DateTimeFormatter SHORT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ProjetService() {
        super(Projet.class);
    }

    public List<Tache> findTachesPlanifiees(Projet projet) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select t from Tache t where t.projet = :projet", Tache.class)
                    .setParameter("projet", projet)
                    .list();
        }
    }

    public List<EmployeTache> findTachesRealiseesAvecDates(Projet projet) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select et from EmployeTache et join fetch et.tache t "
                                    + "where t.projet = :projet order by t.id", EmployeTache.class)
                    .setParameter("projet", projet)
                    .list();
        }
    }

    public void afficherTachesPlanifiees(Projet projet) {
        findTachesPlanifiees(projet).forEach(t ->
                System.out.printf("%d %s %s %s%n", t.getId(), t.getNom(), t.getDateDebut(), t.getDateFin()));
    }

    public void afficherTachesRealiseesAvecDates(Projet projet) {
        System.out.printf("Projet : %d      Nom : %s     Date debut : %s%n",
                projet.getId(), projet.getNom(), projet.getDateDebut().format(SHORT_DATE));
        System.out.println("Liste des taches:");
        System.out.println("Num Nom            Date Debut Reelle   Date Fin Reelle");
        for (EmployeTache employeTache : findTachesRealiseesAvecDates(projet)) {
            Tache tache = employeTache.getTache();
            System.out.printf("%-3d %-14s %-18s %s%n",
                    tache.getId(),
                    tache.getNom(),
                    employeTache.getDateDebutReelle().format(SHORT_DATE),
                    employeTache.getDateFinReelle().format(SHORT_DATE));
        }
    }
}
