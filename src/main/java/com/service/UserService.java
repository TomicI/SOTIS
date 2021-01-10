package com.service;

import com.model.LoginRequest;
import com.model.User;
import com.repository.UserRepository;
import com.security.JWToken;
import com.security.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWToken jwtProvider;

    public String getCurrentUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        return user.get().getUsername();
    }

    public boolean checkIfEmailExists(String email)
    {
        User user = userRepository.findByEmail(email);
        if (user != null)
            return true;
        return false;
    }

    public List<User> search(String text)
    {
        List<User> users = userRepository.findAll();
        List<User> list = new ArrayList<>();

        for (User user : users)
        {
            if (user.getFirstName().contains(text) || user.getLastName().contains(text) || user.getUsername().contains(text))
            {
                list.add(user);
            }
        }
        return list;
    }

    public User getUser(Long id)
    {
        return userRepository.findById(id).get();
    }

    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    public boolean loginCheck(LoginRequest loginRequest)
    {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());

        if (user.isPresent())
        {
            if (user.get().getPassword().equals(loginRequest.getPassword()))
                return true;
        }

        return false;
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        return user;

    }

    public JwtResponse authenticateUser(LoginRequest loginRequest){

        System.out.println("LOGIN " + loginRequest.getUsername() + " " + loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJWToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    }

}
