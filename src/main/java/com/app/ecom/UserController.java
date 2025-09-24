package com.app.ecom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
//    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
//        return ResponseEntity.ok(userService.fetAllUsers());
        return new ResponseEntity<>(userService.fetAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity <User> getUser(@PathVariable Long id){
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity <String> createUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User created");
    }
    @PutMapping("/{id}")
    public ResponseEntity <String> updatedUser(@PathVariable Long id ,@RequestBody User updatedUser){
        boolean updated = userService.updateUser(id, updatedUser);
        if(updated){
            return ResponseEntity.ok("User Updated");
        }
        return ResponseEntity.notFound().build();
    }
}
