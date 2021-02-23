package com.service;

import com.model.Pitanje;
import com.model.Test;
import com.model.User;
import com.repository.PitanjeRepository;
import com.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class TestService
{
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PitanjeRepository pitanjeRepository;

    public Test saveTest (Test test)
    {
        Set<Pitanje> pitanjeSet = new HashSet<>();
        User cUser = userService.getCurrentUser(test.getKreator().getUsername());

        test.setKreator(cUser);

        for(Pitanje pp : test.getPitanja())
        {
            pp = pitanjeRepository.save(pp);
            pitanjeSet.add(pp);
        }

        test.setPitanja(pitanjeSet);

        test = testRepository.save(test);

        return test;
    }
}
