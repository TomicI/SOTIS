package com.controller;

import com.model.*;
import com.repository.ReseniTestRepository;
import com.service.FileService;
import com.service.PitanjeService;
import com.service.TestService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/test")
public class TestController
{
    @Autowired
    private PitanjeService pitanjeService;

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/addQuestion", method = RequestMethod.GET)
    public ResponseEntity<?> saveQuestion(@RequestParam(value="pitanje") String pitanje, @RequestParam(value="odgovori") String odgovori, @RequestParam(value="username") String username)
    {
        User user = this.userService.getCurrentUser(username);

        Pitanje pp = pitanjeService.savePitanje(pitanje, odgovori, user);

        if (pp == null)
            return (ResponseEntity<?>) ResponseEntity.badRequest();

        return ResponseEntity.ok(pp);
    }

    @RequestMapping(value="/addRegions",method=RequestMethod.POST)
    public ResponseEntity<?> updateStatus(@RequestBody Pitanje pitanje)
    {

        Pitanje pp = pitanjeService.addRegions(pitanje);
        if (pp == null)
            return (ResponseEntity<?>) ResponseEntity.badRequest();

        return ResponseEntity.ok(pp);
    }

    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET)
    public ResponseEntity<List<Pitanje>> getQuestions(@RequestParam(value="creatorId") Long creatorId)
    {
        List<Pitanje> pp = pitanjeService.getQuestions(creatorId);

        if (pp == null)
            return (ResponseEntity<List<Pitanje>>) ResponseEntity.badRequest();

        return ResponseEntity.ok(pp);
    }

    @RequestMapping(value="/createTest",method=RequestMethod.POST)
    public ResponseEntity<?> createTest(@RequestBody Test test)
    {

        test = testService.saveTest(test);

        if (test == null)
            return (ResponseEntity<?>) ResponseEntity.badRequest();

        return ResponseEntity.ok(test);
    }

    @RequestMapping(value = "/getTest", method = RequestMethod.GET)
    public ResponseEntity<ReseniTest> getTest(@RequestParam(value="testId") Long testId, @RequestParam(value="username") String username)
    {
        ReseniTest reseniTest = null;

        System.out.println("Username " + username);

        User uu = userService.getCurrentUser(username);

        if (uu == null)
            return (ResponseEntity<ReseniTest>) ResponseEntity.badRequest();
        else
        {
            reseniTest = testService.getTest(uu, testId);

            if (reseniTest == null)
                return (ResponseEntity<ReseniTest>) ResponseEntity.badRequest();
        }

        return ResponseEntity.ok(reseniTest);
    }

    @RequestMapping(value="/reseniTest",method=RequestMethod.PUT)
    public ResponseEntity<ReseniTest> reseniTest(@RequestBody ReseniTest test)
    {

        User user = userService.getUser(test.getUcenik().getId());

        if (user == null)
            return (ResponseEntity<ReseniTest>) ResponseEntity.badRequest();
        else
        {
            test = testService.reseniTest(user, test);

            if (test == null)
                return (ResponseEntity<ReseniTest>) ResponseEntity.badRequest();

            return ResponseEntity.ok(test);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Test>> getAll(@RequestParam(value="username") String username)
    {
        User user = userService.getCurrentUser(username);

        if (user == null)
            return (ResponseEntity<List<Test>>) ResponseEntity.badRequest();
        else
        {
            List<Test> pp = testService.getByKreator(user);

            if (pp == null)
                return (ResponseEntity<List<Test>>) ResponseEntity.badRequest();

            return ResponseEntity.ok(pp);
        }
    }

    @RequestMapping(value = "/getTests", method = RequestMethod.GET)
    public ResponseEntity<List<ReseniTest>> getTests(@RequestParam(value="testId") Long testId, @RequestParam(value="username") String username)
    {
        User user = userService.getCurrentUser(username);

        if (user == null)
            return (ResponseEntity<List<ReseniTest>>) ResponseEntity.badRequest();
        else
        {
            List<ReseniTest> pp = testService.sviReseni(testId, user);

            if (pp == null)
                return (ResponseEntity<List<ReseniTest>>) ResponseEntity.badRequest();

            return ResponseEntity.ok(pp);
        }
    }

    @RequestMapping(value="/updateBodove",method=RequestMethod.PUT)
    public ResponseEntity<ReseniTest> updateBodove(@RequestBody ReseniTest test)
    {

/*      try {
            System.out.println("GET DATA");
            this.testService.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        test = testService.updateBodove(test);

        if (test == null)
            return (ResponseEntity<ReseniTest>) ResponseEntity.badRequest();

        return ResponseEntity.ok(test);

    }

    @RequestMapping(value = "/getAnaliza", method = RequestMethod.GET)
    public ResponseEntity<List<AnalizaPogleda>> getRegions(@RequestParam(value="reseniTestId") Long reseniTestId, @RequestParam(value="username") String username, @RequestParam(value="pitanjeId") Long pitanjeId)
    {
        User user = userService.getCurrentUser(username);

        if (user == null)
            return (ResponseEntity<List<AnalizaPogleda>>) ResponseEntity.badRequest();
        else
        {
            List<AnalizaPogleda> ap = testService.getRegions(reseniTestId, pitanjeId);

            System.out.println("regioni " + ap.size());

            if (ap == null)
                return (ResponseEntity<List<AnalizaPogleda>>) ResponseEntity.badRequest();

            return ResponseEntity.ok(ap);
        }
    }

    @RequestMapping(value = "/getAllUradjene", method = RequestMethod.GET)
    public ResponseEntity<List<ReseniTest>> getAllUradjene(@RequestParam(value="username") String username)
    {
        User user = userService.getCurrentUser(username);

        if (user == null)
            return (ResponseEntity<List<ReseniTest>>) ResponseEntity.badRequest();
        else
        {
            List<ReseniTest> pp = testService.getUradjene(user);

            if (pp == null)
                return (ResponseEntity<List<ReseniTest>>) ResponseEntity.badRequest();

            return ResponseEntity.ok(pp);
        }
    }
}
