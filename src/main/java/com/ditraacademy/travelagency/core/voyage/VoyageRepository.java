package com.ditraacademy.travelagency.core.voyage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage,Integer> { // Integer lel id mta3 User type int
    List<Voyage> getVoyageByPrixBetweenAndNbPlacesIsNot(double minPrice,double maxPrice,int nbPlace);

    @Query(value = "select * from voyages where nbPlaces ?1",nativeQuery = true)
    List<Voyage> getVoyages(@Param("nb") int nb);
}
