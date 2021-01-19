package com.service;

import com.model.Odgovor;
import com.model.Pitanje;
import com.repository.OdgovorRepository;
import com.repository.PitanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class PitanjeService
{
    @Autowired
    private PitanjeRepository pitanjeRepository;

    @Autowired
    private OdgovorRepository odgovorRepository;


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
}
