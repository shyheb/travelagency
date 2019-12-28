package com.ditraacademy.travelagency.core.hotel;

import com.ditraacademy.travelagency.core.chambre.chambre.Chambre;
import com.ditraacademy.travelagency.utility.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "update Hotel set deleted=true where id=?")
public class Hotel extends Audible<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String description;
    private Integer etoile;
    private String adresse;
    private Integer telephone;

    @ManyToMany
    @JoinTable(
            name = "hotel_chambre",
            joinColumns = {@JoinColumn(name="hotel_id")},
            inverseJoinColumns = {@JoinColumn(name = "chambre_id")}
    )
    private List<Chambre> chambres;

    @JsonIgnore
    private Boolean deleted = false;

    public void addChambre(Chambre chambre){
        chambres.add(chambre);
    }

    public void removeChambre(int id){
        chambres.removeIf(chambre -> chambre.getId() == id);
    }


}
