package se331.olympicsbackend.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se331.olympicsbackend.util.LabMapper;

@Controller
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

    @GetMapping("users")
    public ResponseEntity<?> getUsers(
            @RequestParam(value = "_limit",required = false,defaultValue = "10") Integer perPage,
            @RequestParam(value = "_page",required = false,defaultValue = "1") Integer page
    ){
        perPage=perPage==null?10:perPage;
        page=page==null?1:page;
        Page<User> pageOutput;

        pageOutput = userService.getUsers(perPage,page);

       HttpHeaders responseHeader=new HttpHeaders();
       responseHeader.set("x-total-count",String.valueOf(pageOutput.getTotalElements()));
       return new ResponseEntity<>
               (LabMapper.INSTANCE.getUserDTO(pageOutput.getContent()),
               responseHeader,
               HttpStatus.OK);

    }


}
