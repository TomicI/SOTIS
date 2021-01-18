package com.controller;

import com.model.Odgovor;
import com.model.Pitanje;
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
    @RequestMapping(value = "/addQuestion", method = RequestMethod.GET)
    public ResponseEntity<?> authenticateUser(@RequestParam(value="pitanje") String pitanje, @RequestParam(value="odgovori") String odgovori)
    {
        Pitanje pp = new Pitanje();
        Set<Odgovor> odgovoriList= null;
        pp.setTekstPitanja(pitanje);

        if (odgovori != "" && odgovori != null)
        {
            odgovoriList = new HashSet<Odgovor>();
            try
            {

                String temp[] = odgovori.split("#");

                for (int ii = 1; ii < temp.length; ii++)
                {
                    String temp2[] = temp[ii].split("\\*");

                    Odgovor oo = new Odgovor();
                    oo.setPitanje(pp);
                    oo.setTekstOdgovora(temp2[0]);
                    oo.setBrojBodova(Integer.parseInt(temp2[1]));
                    if (temp2[2].equals("T"))
                        oo.setTacan(true);
                    else
                        oo.setTacan(false);

                    odgovoriList.add(oo);
                }

                pp.setOdgovori(odgovoriList);
            }catch (Exception e)
            {
                return (ResponseEntity<?>) ResponseEntity.badRequest();
            }


        }

        return ResponseEntity.ok(pp);
    }
}
