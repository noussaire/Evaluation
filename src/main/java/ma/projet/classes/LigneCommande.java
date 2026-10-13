package ma.projet.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantite;

    @ManyToOne
    private Produit produit;

    @ManyToOne
    private Commande commande;

    public LigneCommande() {
    }

    public LigneCommande(Produit produit, Commande commande, int quantite) {
        this.produit = produit;
        this.commande = commande;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
