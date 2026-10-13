package ma.projet.util;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.LigneCommande;
import ma.projet.classes.Produit;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.util.Properties;

public final class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties();
            try (InputStream input = HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {
                if (input == null) {
                    throw new IllegalStateException("application.properties introuvable");
                }
                properties.load(input);
            }

            Configuration configuration = new Configuration();
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(Categorie.class);
            configuration.addAnnotatedClass(Produit.class);
            configuration.addAnnotatedClass(Commande.class);
            configuration.addAnnotatedClass(LigneCommande.class);
            configuration.addAnnotatedClass(Projet.class);
            configuration.addAnnotatedClass(Tache.class);
            configuration.addAnnotatedClass(Employe.class);
            configuration.addAnnotatedClass(EmployeTache.class);
            configuration.addAnnotatedClass(Homme.class);
            configuration.addAnnotatedClass(Femme.class);
            configuration.addAnnotatedClass(Mariage.class);

            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            return configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        SESSION_FACTORY.close();
    }
}
