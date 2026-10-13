package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class FemmeService extends AbstractService<Femme> {
    public FemmeService() {
        super(Femme.class);
    }

    public Femme findPlusAgee() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Femme f order by f.dateNaissance asc", Femme.class)
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }

    public int nombreEnfantsEntreDates(Femme femme, LocalDate debut, LocalDate fin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Object result = session.createNamedQuery("Femme.nombreEnfantsEntreDates")
                    .setParameter("femmeId", femme.getId())
                    .setParameter("debut", debut)
                    .setParameter("fin", fin)
                    .uniqueResult();
            if (result instanceof BigInteger) {
                return ((BigInteger) result).intValue();
            }
            if (result instanceof BigDecimal) {
                return ((BigDecimal) result).intValue();
            }
            if (result instanceof Number) {
                return ((Number) result).intValue();
            }
            return 0;
        }
    }

    public List<Femme> findMarieesDeuxFoisOuPlus() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Femme.marieesDeuxFoisOuPlus", Femme.class).list();
        }
    }
}
