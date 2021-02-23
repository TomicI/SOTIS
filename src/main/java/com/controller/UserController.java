package com.controller;

import com.model.LoginRequest;
import com.model.Pitanje;
import com.model.User;
import com.security.JwtResponse;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    UserService userService;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        if (userService.loginCheck(loginRequest)) {
            JwtResponse r = null;

            try {
                r = userService.authenticateUser(loginRequest);
                return ResponseEntity.ok(r);
            } catch (Exception e)
            {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Wrong username or password");
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");
    }

    @RequestMapping(value = "/getCUser", method = RequestMethod.GET)
    public ResponseEntity<User> getCUser(@RequestParam(value="username") String username)
    {

        System.out.println("Username " + username);

        User uu = userService.getCurrentUser(username);

        if (uu == null)
            return (ResponseEntity<User>) ResponseEntity.badRequest();

        return ResponseEntity.ok(uu);
    }
}
