package se331.olympicsbackend.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.Sport;

import java.util.List;

public interface SportRepository extends JpaRepository<Sport, Integer> {
    List<Sport> findAll();
}
