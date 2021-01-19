package com.controller;

import com.model.Odgovor;
import com.model.Pitanje;
import com.service.PitanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/test")
public class TestController
{
    @Autowired
    private PitanjeService pitanjeService;
    @RequestMapping(value = "/addQuestion", method = RequestMethod.GET)
    public ResponseEntity<?> authenticateUser(@RequestParam(value="pitanje") String pitanje, @RequestParam(value="odgovori") String odgovori)
    {
        Pitanje pp = pitanjeService.savePitanje(pitanje, odgovori);

        if (pp == null)
            return (ResponseEntity<?>) ResponseEntity.badRequest();

        return ResponseEntity.ok(pp);
    }
}
