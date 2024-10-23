package se331.olympicsbackend.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.Medal;

import java.util.List;

public interface MedalRepository extends JpaRepository<Medal, Long> {
    List<Medal> findAll();


}
