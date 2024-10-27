package se331.olympicsbackend.rest.repository;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.Medal;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedalRepository extends JpaRepository<Medal, Long> {
    List<Medal> findAll();
    Optional<Medal> findById(Long id);
    Medal findByCountry(Country country);



    @Query("SELECT m FROM Medal m WHERE m.country.id = :countryId AND m.sport.id = :sportId")
    Optional<Medal> findByCountryIdAndSportId(@Param("countryId") Long countryId, @Param("sportId") Integer sportId);
}
