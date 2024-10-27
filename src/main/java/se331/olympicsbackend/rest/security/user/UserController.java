package se331.olympicsbackend.rest.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se331.olympicsbackend.rest.util.LabMapper;

@Controller
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

    @GetMapping("users")
    public ResponseEntity<?> getUsers(
            @RequestParam(value = "_limit",required = false,defaultValue = "3") Integer perPage,
            @RequestParam(value = "_page",required = false,defaultValue = "1") Integer page
    ){
        Page<User> pageOutput= userService.getUsers(perPage,page);
       HttpHeaders responseHeader=new HttpHeaders();
       responseHeader.set("x-total-count",String.valueOf(pageOutput.getTotalElements()));
       return new ResponseEntity<>
               (LabMapper.INSTANCE.getUserDTO(pageOutput.getContent()),
               responseHeader,
               HttpStatus.OK);

    }
    @PutMapping("users/{id}/role")

    public ResponseEntity<?> updateUserRole(

            @PathVariable("id") int id,

            @RequestBody UserDTO userDTO

    ){

        User updatedUser= userService.updateUserRole(id, userDTO);

        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(updatedUser));

    }

}
