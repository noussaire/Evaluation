package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class MariageService extends AbstractService<Mariage> {
    public MariageService() {
        super(Mariage.class);
    }

    public long nombreHommesMariesAQuatreFemmesEntreDates(LocalDate debut, LocalDate fin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<Homme> homme = query.from(Homme.class);
            Join<Homme, Mariage> mariage = homme.join("mariages");

            query.select(cb.countDistinct(homme));
            query.where(cb.between(mariage.get("dateDebut"), debut, fin));
            query.groupBy(homme.get("id"));
            query.having(cb.ge(cb.countDistinct(mariage.get("femme")), 4L));

            return session.createQuery(query).list().size();
        }
    }
}
