package com.ecommerce.user.controllers;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    //private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        //System.out.println("REQUEST RECEIVED");
        return new ResponseEntity<>(userService.fetAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity <UserResponse> getUser(@PathVariable String id){
//        logger.info("Fetching user with id {}", id);
//        logger.trace("Fetching user with id {}", id);
//        logger.debug("Fetching user with id {}", id);
//        logger.info("Fetching user with id {}", id);
//        logger.warn("Fetching user with id {}", id);
//        logger.error("Fetching user with id {}", id);
        log.info("Fetching user with id {}", id);
        log.trace("Fetching user with id {}", id);
        log.debug("Fetching user with id {}", id);
        log.info("Fetching user with id {}", id);
//        log.warn("Fetching user with id {}", id);
//        log.error("Fetching user with id {}", id);

        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity <String> createUser(@RequestBody UserRequest userRequest){
        userService.addUser(userRequest);
        return ResponseEntity.ok("User created");
    }
    @PutMapping("/{id}")
    public ResponseEntity <String> updatedUser(@PathVariable String id ,@RequestBody UserRequest updatedUserRequest){
        boolean updated = userService.updateUser(id, updatedUserRequest);
        if(updated){
            return ResponseEntity.ok("User Updated");
        }
        return ResponseEntity.notFound().build();
    }
}
