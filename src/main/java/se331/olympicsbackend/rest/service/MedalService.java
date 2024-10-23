package se331.olympicsbackend.rest.service;

import se331.olympicsbackend.rest.entity.Medal;

import java.util.List;

public interface MedalService {
    void fetchAndStoreMedals(); // Fetch mock API data and store it into the database
    List<Medal> getAllMedals();

}
