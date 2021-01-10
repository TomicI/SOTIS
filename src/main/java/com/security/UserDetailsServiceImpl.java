package com.security;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import com.security.JWToken;
import com.security.JwtResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JWToken jwtProvider;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        return user;

    }
/*
    public void changePass(String oldPass,String newPass) {

        Authentication current = SecurityContextHolder.getContext().getAuthentication();
        String username = current.getName();

        if (authenticationManager !=null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,oldPass));
        }else {
            return;
        }

        Optional<User> userOpt = userRepository.findByUsername(username);

        userOpt.get().setPassword(passwordEncoder.encode(newPass));
        userOpt.get().setLastPasswordResetDate(new Date());
        userRepository.save(userOpt.get());

    }

    public boolean changeAdminPass(String newPass){

        Authentication current = SecurityContextHolder.getContext().getAuthentication();
        String username = current.getName();

        Optional<User> userOpt = userRepository.findByUsername(username);

        if (!userOpt.get().isReset()){
            userOpt.get().setPassword(passwordEncoder.encode(newPass));
            userOpt.get().setLastPasswordResetDate(new Date());
            userOpt.get().setReset(true);
            userRepository.save(userOpt.get());
            return true;
        }

        return false;

    }

    public JwtResponse changeUsername(SignUpForm signUpRequest){

        if (signUpRequest.getUsername().isEmpty() || signUpRequest.getUsername().length() < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Minimum 3 characters!");
        }

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        Authentication current = SecurityContextHolder.getContext().getAuthentication();
        String oldUsername = current.getName();

        User userDet = (User) current.getPrincipal();
        userDet.setUsername(signUpRequest.getUsername());

        String jwt = jwtProvider.generateJWToken(current);
        UserDetails userDetails = (UserDetails) current.getPrincipal();

        Optional<User> userOpt = userRepository.findByUsername(oldUsername);
        userOpt.get().setUsername(signUpRequest.getUsername());
        userRepository.save(userOpt.get());

        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());

    }


    public void changeName(String first,String last){

        if (first.length() < 3 || last.length() < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Minimum 3 characters!");
        }

        if (first.length() > 20 || last.length() > 20) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maximum 20 characters!");
        }

        Authentication current = SecurityContextHolder.getContext().getAuthentication();
        String username = current.getName();

        if (authenticationManager == null){
            return;
        }

        User ud = (User) loadUserByUsername(username);
        ud.setFirstName(first);
        ud.setLastName(last);

        Optional<User> userOpt = userRepository.findByUsername(username);
        userOpt.get().setFirstName(first);
        userOpt.get().setLastName(last);

        userRepository.save(userOpt.get());
    }

    public void changeEmail(SignUpForm signUpRequest){

        if (signUpRequest.getEmail().isEmpty()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't be empty!");
        }

        if (signUpRequest.getEmail().length() > 60) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Max 60 characters!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already in use!");
        }

        String email = signUpRequest.getEmail();

        Authentication current = SecurityContextHolder.getContext().getAuthentication();
        String username = current.getName();

        if (authenticationManager == null){
            return;
        }

        User ud = (User) loadUserByUsername(username);
        ud.setEmail(email);

        Optional<User> userOpt = userRepository.findByUsername(username);
        userOpt.get().setEmail(email);


        userRepository.save(userOpt.get());
    }
*/


}