package com.service;

import com.model.Odgovor;
import com.model.Pitanje;
import com.model.Regioni;
import com.repository.OdgovorRepository;
import com.repository.PitanjeRepository;
import com.repository.RegioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PitanjeService
{
    @Autowired
    private PitanjeRepository pitanjeRepository;

    @Autowired
    private OdgovorRepository odgovorRepository;

    @Autowired
    private RegioniRepository regioniRepository;

    public Pitanje savePitanje(String pitanje, String odgovori)
    {
        Pitanje pp = new Pitanje();
        Set<Odgovor> odgovoriList= null;
        pp.setTekstPitanja(pitanje);

        pp = pitanjeRepository.save(pp);

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

                    oo.setPitanje(pp);

                    oo = odgovorRepository.save(oo);

                    odgovoriList.add(oo);
                }

                pp.setOdgovori(odgovoriList);
            }catch (Exception e)
            {
                return null;
            }
        }
        return pitanjeRepository.save(pp);
    }

    public Pitanje addRegions (Pitanje pitanje)
    {
        Optional<Pitanje> p = pitanjeRepository.findById(pitanje.getId());

        ArrayList<Regioni> regioni = new ArrayList<>(pitanje.getRegioni());

        if (p.isPresent())
        {
            for(int ii = 0; ii < regioni.size(); ii++)
            {
                regioni.get(ii).setPitanjeId(p.get().getId());
                regioni.set(ii, regioniRepository.save(regioni.get(ii)));
            }
            pitanje.setRegioni(new HashSet<>(regioni));
            pitanje = pitanjeRepository.save(pitanje);
        }
        else
            return null;

        return pitanje;
    }
}
