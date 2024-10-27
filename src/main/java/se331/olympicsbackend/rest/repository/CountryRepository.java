package se331.olympicsbackend.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se331.olympicsbackend.rest.entity.Country;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @EntityGraph(attributePaths = {"medal"})
    List<Country> findAll();
    Optional<Country> findByCountryName(String countryName);
    Optional<Country> findById(Long id);
    Page<Country> findByCountryName(String title, Pageable pageRequest);

    @Query("SELECT c FROM Country c LEFT JOIN FETCH c.sports")
    List<Country> findAllWithSports();
}
