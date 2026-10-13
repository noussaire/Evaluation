package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class TacheService extends AbstractService<Tache> {
    public TacheService() {
        super(Tache.class);
    }

    public List<Tache> findPrixSuperieurA1000() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Tache.prixSuperieurA1000", Tache.class).list();
        }
    }

    public List<Tache> findRealiseesEntreDates(LocalDate debut, LocalDate fin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select distinct et.tache from EmployeTache et "
                                    + "where et.dateDebutReelle >= :debut and et.dateFinReelle <= :fin",
                            Tache.class)
                    .setParameter("debut", debut)
                    .setParameter("fin", fin)
                    .list();
        }
    }

    public void afficherTachesPrixSuperieurA1000() {
        findPrixSuperieurA1000().forEach(t ->
                System.out.printf("%d %s %.2f DH%n", t.getId(), t.getNom(), t.getPrix()));
    }

    public void afficherTachesRealiseesEntreDates(LocalDate debut, LocalDate fin) {
        findRealiseesEntreDates(debut, fin).forEach(t ->
                System.out.printf("%d %s%n", t.getId(), t.getNom()));
    }
}
