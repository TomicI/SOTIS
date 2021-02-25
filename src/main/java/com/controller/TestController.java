package com.controller;

import com.model.Odgovor;
import com.model.Pitanje;
import com.model.Test;
import com.service.PitanjeService;
import com.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/test")
public class TestController
{
    @Autowired
    private PitanjeService pitanjeService;

    @Autowired
    private TestService testService;


    @RequestMapping(value = "/addQuestion", method = RequestMethod.GET)
    public ResponseEntity<?> saveQuestion(@RequestParam(value="pitanje") String pitanje, @RequestParam(value="odgovori") String odgovori)
    {
        Pitanje pp = pitanjeService.savePitanje(pitanje, odgovori);

        if (pp == null)
            return (ResponseEntity<?>) ResponseEntity.badRequest();

        return ResponseEntity.ok(pp);
    }

    @RequestMapping(value="/addRegions",method=RequestMethod.POST)
    public ResponseEntity<?> updateStatus(@RequestBody Pitanje pitanje){

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
    public ResponseEntity<?> createTest(@RequestBody Test test){

        test = testService.saveTest(test);

        if (test == null)
            return (ResponseEntity<?>) ResponseEntity.badRequest();

        return ResponseEntity.ok(test);
    }
}
