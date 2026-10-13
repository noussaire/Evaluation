package ma.projet.test;

import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommande;
import ma.projet.classes.Produit;
import ma.projet.service.CategorieService;
import ma.projet.service.CommandeService;
import ma.projet.service.LigneCommandeService;
import ma.projet.service.ProduitService;
import ma.projet.util.HibernateUtil;

import java.time.LocalDate;

public class TestExercice1 {
    public static void main(String[] args) {
        CategorieService categorieService = new CategorieService();
        ProduitService produitService = new ProduitService();
        CommandeService commandeService = new CommandeService();
        LigneCommandeService ligneCommandeService = new LigneCommandeService();

        Categorie composants = new Categorie("COMP", "Composants");
        Categorie accessoires = new Categorie("ACC", "Accessoires");
        categorieService.create(composants);
        categorieService.create(accessoires);

        Produit p1 = new Produit("ES12", 120, composants);
        Produit p2 = new Produit("ZR85", 100, composants);
        Produit p3 = new Produit("EE85", 200, accessoires);
        produitService.create(p1);
        produitService.create(p2);
        produitService.create(p3);

        Commande c1 = new Commande(LocalDate.of(2013, 3, 14));
        Commande c2 = new Commande(LocalDate.of(2013, 4, 10));
        commandeService.create(c1);
        commandeService.create(c2);

        ligneCommandeService.create(new LigneCommande(p1, c1, 7));
        ligneCommandeService.create(new LigneCommande(p2, c1, 14));
        ligneCommandeService.create(new LigneCommande(p3, c1, 5));
        ligneCommandeService.create(new LigneCommande(p3, c2, 2));

        System.out.println("Produits par categorie :");
        produitService.afficherProduitsParCategorie(composants);

        System.out.println("\nProduits commandes entre deux dates :");
        produitService.afficherProduitsCommandesEntreDates(
                LocalDate.of(2013, 3, 1), LocalDate.of(2013, 3, 31));

        System.out.println("\nProduits d'une commande :");
        produitService.afficherProduitsDansCommande(c1);

        System.out.println("\nProduits dont le prix est superieur a 100 DH :");
        produitService.findPrixSuperieurA100().forEach(p ->
                System.out.printf("%s %.2f DH%n", p.getReference(), p.getPrix()));

        HibernateUtil.shutdown();
    }
}
