package com.ditraacademy.travelagency.core.chambre.chambre;

import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategoryChambre;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambre;
import com.ditraacademy.travelagency.core.hotel.Hotel;
import com.ditraacademy.travelagency.utility.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "update Chambre set deleted=true where id=?")
@Table(uniqueConstraints ={
        @UniqueConstraint(columnNames = {"category_chambre_id","type_chambre_id"})
})
public class Chambre extends Audible<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private TypeChambre typeChambre;

    @ManyToOne
    private CategoryChambre categoryChambre;

    @JsonIgnore
    @ManyToMany(mappedBy = "chambres")
    private List<Hotel> hotels;

    @JsonIgnore
    private boolean deleted=false;


}
