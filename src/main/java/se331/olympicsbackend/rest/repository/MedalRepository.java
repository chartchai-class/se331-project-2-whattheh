package se331.olympicsbackend.rest.repository;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.Medal;

import java.util.List;

@Repository
public interface MedalRepository extends JpaRepository<Medal, Long> {




}
