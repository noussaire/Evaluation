package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommande;
import ma.projet.classes.Produit;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ProduitService extends AbstractService<Produit> {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);

    public ProduitService() {
        super(Produit.class);
    }

    public List<Produit> findByCategorie(Categorie categorie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select p from Produit p where p.categorie = :categorie", Produit.class)
                    .setParameter("categorie", categorie)
                    .list();
        }
    }

    public List<Produit> findCommandesEntreDates(LocalDate debut, LocalDate fin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select distinct lc.produit from LigneCommande lc "
                                    + "where lc.commande.date between :debut and :fin", Produit.class)
                    .setParameter("debut", debut)
                    .setParameter("fin", fin)
                    .list();
        }
    }

    public List<LigneCommande> findProduitsDansCommande(Commande commande) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select lc from LigneCommande lc join fetch lc.produit "
                                    + "where lc.commande = :commande", LigneCommande.class)
                    .setParameter("commande", commande)
                    .list();
        }
    }

    public List<Produit> findPrixSuperieurA100() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Produit.prixSuperieurA100", Produit.class).list();
        }
    }

    public void afficherProduitsParCategorie(Categorie categorie) {
        findByCategorie(categorie).forEach(p ->
                System.out.printf("%s %.2f DH%n", p.getReference(), p.getPrix()));
    }

    public void afficherProduitsCommandesEntreDates(LocalDate debut, LocalDate fin) {
        findCommandesEntreDates(debut, fin).forEach(p ->
                System.out.printf("%s %.2f DH%n", p.getReference(), p.getPrix()));
    }

    public void afficherProduitsDansCommande(Commande commande) {
        System.out.printf("Commande : %d     Date : %s%n", commande.getId(), commande.getDate().format(FORMATTER));
        System.out.println("Liste des produits :");
        System.out.println("Reference   Prix    Quantite");
        for (LigneCommande ligne : findProduitsDansCommande(commande)) {
            Produit produit = ligne.getProduit();
            System.out.printf("%-10s %.0f DH  %d%n",
                    produit.getReference(), produit.getPrix(), ligne.getQuantite());
        }
    }
}
