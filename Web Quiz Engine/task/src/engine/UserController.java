package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    

    @Autowired
    private UserService userService;

    @PostMapping(path = "/api/register")
    public void register(@Valid @RequestBody User user) {
        userService.save(user);
    }
}
