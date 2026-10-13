package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EmployeService extends AbstractService<Employe> {
    public EmployeService() {
        super(Employe.class);
    }

    public List<Tache> findTachesRealisees(Employe employe) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select distinct et.tache from EmployeTache et where et.employe = :employe",
                            Tache.class)
                    .setParameter("employe", employe)
                    .list();
        }
    }

    public List<Projet> findProjetsGeres(Employe employe) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select p from Projet p where p.chef = :employe", Projet.class)
                    .setParameter("employe", employe)
                    .list();
        }
    }

    public void afficherTachesRealisees(Employe employe) {
        findTachesRealisees(employe).forEach(t ->
                System.out.printf("%d %s %.2f DH%n", t.getId(), t.getNom(), t.getPrix()));
    }

    public void afficherProjetsGeres(Employe employe) {
        findProjetsGeres(employe).forEach(p ->
                System.out.printf("%d %s%n", p.getId(), p.getNom()));
    }
}
