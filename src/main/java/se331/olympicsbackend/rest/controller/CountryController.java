package se331.olympicsbackend.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CountryController {

    @GetMapping("/home")
    public ResponseEntity<?> getEventLists(
            @RequestParam(value = "_limit",required = false, defaultValue = "10") Integer perPage,
            @RequestParam(value = "_page",required = false,defaultValue = "1") Integer page

    ){
        return null;
    }
}
