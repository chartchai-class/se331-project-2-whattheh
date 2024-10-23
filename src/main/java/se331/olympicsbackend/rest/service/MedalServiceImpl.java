package se331.olympicsbackend.rest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se331.olympicsbackend.rest.entity.Medal;
import se331.olympicsbackend.rest.repository.MedalRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class MedalServiceImpl implements MedalService {
      MedalRepository medalRepository;
      RestTemplate restTemplate;
    @Override
    public void fetchAndStoreMedals() {
        String apiUrl = "https://cfaef2cc-2a38-4135-b81b-a179cf52e24d.mock.pstmn.io/demo";
        Medal[] medals = restTemplate.getForObject(apiUrl, Medal[].class);
        System.out.println("Length:"+ medals.length+ "and Medals:"+ Arrays.toString(medals));
        if (medals != null) {
            medalRepository.saveAll(Arrays.asList(medals));
        }
    }

    @Override
    public List<Medal> getAllMedals() {
        return medalRepository.findAll();    }
}
