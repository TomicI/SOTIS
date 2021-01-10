package com.service;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
}
