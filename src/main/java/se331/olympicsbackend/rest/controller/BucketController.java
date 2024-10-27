package se331.olympicsbackend.rest.controller;

import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import se331.olympicsbackend.rest.util.CloudStorageHelper;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class BucketController {
    final CloudStorageHelper cloudStorageHelper;
    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException, ServletException {
        return ResponseEntity.ok(this.cloudStorageHelper.getImageUrl(file,"imageproj2compo.appspot.com"));
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadFileComponent(@RequestPart(value = "image") MultipartFile file) throws IOException, ServletException {
        return ResponseEntity.ok(this.cloudStorageHelper.getStorageFileDto(file,"imageproj2compo.appspot.com"));
    }
}
