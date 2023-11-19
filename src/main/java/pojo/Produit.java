package pojo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Produit {
	@Id
    private String nom;
    private TypeContenu type;
    @ManyToMany(mappedBy="produits")
    private List<Automate> automate;
    
    public Produit() {}
    
    public Produit(String nom, TypeContenu type) {
        this.nom = nom;
        this.type = type;
    }
   
    public String getNom() { return nom; }
    public TypeContenu getType() { return type; }
}
