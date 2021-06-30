package com.service;

import com.model.*;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class TestService {
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PitanjeRepository pitanjeRepository;

    @Autowired
    private ReseniTestRepository reseniTestRepository;

    @Autowired
    private OdgovorRepository odgovorRepository;

    @Autowired
    private EyetrackerRecordRepository eyetrackerRecordRepository;

    @Autowired
    private AnalizaPogledaRepository analizaPogledaRepository;

    public Test saveTest(Test test) {
        Set<Pitanje> pitanjeSet = new HashSet<>();
        User cUser = userService.getCurrentUser(test.getKreator().getUsername());

        test.setKreator(cUser);

        for (Pitanje pp : test.getPitanja()) {
            pp = pitanjeRepository.save(pp);
            pitanjeSet.add(pp);
        }

        test.setPitanja(pitanjeSet);

        test = testRepository.save(test);

        cUser.getKreiraniTestovi().add(test);
        userService.saveUser(cUser);

        return test;
    }

    public ReseniTest getTest(User cUser, Long testId) {
        Optional<Test> test = testRepository.findById(testId);
        ReseniTest reseniTest = null;

        if (test.isPresent()) {
            System.out.println("Nadjen test " + test.get().getId());
            if (reseniTestRepository.findByTestIdAndUcenikId(test.get().getId(), cUser.getId()).isEmpty()) {
                System.out.println("Nije ga radio user ");
                System.out.println("user id " + cUser.getId());
                reseniTest = new ReseniTest();
                reseniTest.setDatumVremePocetak(new Timestamp(System.currentTimeMillis()));
                reseniTest.setUcenik(cUser);
                reseniTest.setTest(test.get());
                reseniTest = reseniTestRepository.save(reseniTest);

                System.out.println("reseni test id " + reseniTest.getId());

                cUser.getReseniTestovi().add(reseniTest);
                userService.saveUser(cUser);

                return reseniTest;
            }
        }

        return reseniTest;
    }

    public ReseniTest reseniTest(User cUser, ReseniTest reseniTest) {
        int brojBodova = 0;
        Set<Odgovor> odgovorSet = new HashSet<>();

        if (!reseniTestRepository.findByTestIdAndUcenikId(reseniTest.getTest().getId(), cUser.getId()).isEmpty()) {
            reseniTest.setDatumVremeKraj(new Timestamp(System.currentTimeMillis()));

            for (Odgovor oo : reseniTest.getOdgovorSet()) {
                if (oo.getId() != 0)
                    brojBodova += oo.getBrojBodova();
                else {
                    Pitanje p = pitanjeRepository.findById((long) oo.getBrojBodova()).get();
                    oo.setBrojBodova(0);
                    oo.setPitanje(p);
                    oo = odgovorRepository.save(oo);

                    odgovorSet.add(oo);

                    System.out.println(oo.getId());
                    System.out.println(oo.getTekstOdgovora());
                }
            }

            reseniTest.setOdgovorSet(odgovorSet);

            reseniTest.setBrojOstvarenihBodova(brojBodova);

            reseniTest = reseniTestRepository.save(reseniTest);

            try {
                reseniTest.setRecords(getData());
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("eyetracker record nazad " + reseniTest.getRecords().size() + " reseni test " + reseniTest.getId()) ;
            reseniTest = reseniTestRepository.save(reseniTest);

            analizeTest(reseniTest.getId());

            return reseniTest;
        }

        return null;
    }

    public List<ReseniTest> sviReseni(Long testId, User user) {
        if (user.getRole().equals("admin")) {
            Test test = testRepository.findById(testId).get();

            if (test.getKreator().getId() == user.getId()) {
                return reseniTestRepository.findByTestId(testId);
            } else
                return null;
        } else
            return null;
    }

    public List<Test> getByKreator(User u) {
        if (u.getRole().equals("admin"))
            return testRepository.findByKreatorId(u.getId());
        else
            return null;
    }

    public ReseniTest updateBodove(ReseniTest test) {
        int noviBodovi = 0;

        System.out.println("osg set size " + test.getOdgovorSet().size());
        for (Odgovor oo : test.getOdgovorSet()) {

            Odgovor temp = odgovorRepository.findById(oo.getId()).get();
            System.out.println("pitanje id " + temp.getPitanje().getId());

            if (temp.getBrojBodova() != oo.getBrojBodova()) {
                if (temp.getBrojBodova() != 0) {
                    noviBodovi -= temp.getBrojBodova();
                    System.out.println("Novi bodovi " + noviBodovi);
                }

                temp.setBrojBodova(oo.getBrojBodova());
                System.out.println("bodovi");
                System.out.println(temp.getBrojBodova());
                temp = odgovorRepository.save(temp);

                noviBodovi += temp.getBrojBodova();
                System.out.println("Novi bodovi posle " + noviBodovi);

                System.out.println(temp.getId());
                System.out.println(temp.getBrojBodova());
            }


        }

        test.setBrojOstvarenihBodova(test.getBrojOstvarenihBodova() + noviBodovi);
        test = reseniTestRepository.save(test);

        return test;

    }

    private Set<EyetrackerRecord> getData() throws IOException
    {
        String row = "";
        Set<EyetrackerRecord> records = new HashSet<>();
        int n = 0;
        BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\Ivic\\Documents\\eyetracker\\Output\\User_2.csv"));
        while ((row = csvReader.readLine()) != null)
        {
            if (n > 0) {
                String[] data = row.split(",");
                EyetrackerRecord eyetrackerRecord = null;
                try {
                    eyetrackerRecord = new EyetrackerRecord();
                    eyetrackerRecord.setAll(new Timestamp((long) (Double.valueOf(data[1]) * 1000)), Double.valueOf(data[2]), Double.valueOf(data[3]), Double.valueOf(data[4]), Double.valueOf(data[5]));

                    eyetrackerRecord = eyetrackerRecordRepository.save(eyetrackerRecord);
                } catch (Exception e) {
                    System.out.println("DATA MISSING !");
                    System.out.println(e);
                }
                if (eyetrackerRecord != null)
                {
                    System.out.println("eyetracker record " + eyetrackerRecord.getId() + " timestamp " + eyetrackerRecord.getTimestamp()) ;
                    records.add(eyetrackerRecord);
                }



            }
            n++;
        }
        csvReader.close();

        System.out.println("eyetracker records sum " + n + " in records " + records.size()) ;
        return records;
    }

    private void analizeTest(Long reseniTestId)
    {
        Optional<ReseniTest> reseniTest = reseniTestRepository.findById(reseniTestId);
        int nL = 0;
        int nR = 0;

        System.out.println("################reseni test " + reseniTest.get().getRecords().size());

        if(reseniTest.isPresent())
        {
            if(!reseniTest.get().getRecords().isEmpty())
            {
                for (EyetrackerRecord eyetrackerRecord : reseniTest.get().getRecords())
                {
                    for (PitanjeTimestamp pitanjeTimestamp: reseniTest.get().getPitanjeRecords())
                    {
                        if (eyetrackerRecord.getTimestamp().compareTo(pitanjeTimestamp.getTimestampStart()) > 0 && eyetrackerRecord.getTimestamp().compareTo(pitanjeTimestamp.getTimestampEnd()) < 0)
                        {

                            for (Regioni regioni : pitanjeTimestamp.getPitanje().getRegioni())
                            {
/*                              if(eyetrackerRecord.getLeftEyeX() == null)
                                {
                                    System.out.println("LEFT X NULL");
                                }else if(regioni.getX0() == null)
                                {
                                    System.out.println("region X0  NULL");
                                }else if(regioni.getWidth() == null)
                                {
                                    System.out.println("region Width  NULL");
                                } else if(eyetrackerRecord.getLeftEyeY() == null)
                                {
                                    System.out.println("LEFT Y NULL");
                                }else if(regioni.getY0() == null)
                                {
                                    System.out.println("region Y0  NULL");
                                }else if(regioni.getHeight() == null)
                                {
                                    System.out.println("region Height  NULL");
                                }

 */
                                if (eyetrackerRecord.getLeftEyeX()*100 >= regioni.getX0() && eyetrackerRecord.getLeftEyeX()*100 <= (regioni.getX0() + regioni.getWidth()) && eyetrackerRecord.getLeftEyeY()*100 >= regioni.getY0() && eyetrackerRecord.getLeftEyeY()*100 <= (regioni.getY0() + regioni.getHeight()))
                                {
                                    AnalizaPogleda analizaPogleda = new AnalizaPogleda(eyetrackerRecord.getLeftEyeX(), eyetrackerRecord.getLeftEyeY(), pitanjeTimestamp.getPitanje(), reseniTest.get(), regioni.getId(), eyetrackerRecord.getTimestamp());
                                    analizaPogleda = analizaPogledaRepository.save(analizaPogleda);
                                    nL++;
                                }
/*                              else
                                {
                                    System.out.println("Left Eye x " + eyetrackerRecord.getLeftEyeX());
                                    System.out.println("Left Eye y " + eyetrackerRecord.getLeftEyeY());
                                    System.out.println("Regioni Xo, Yo " +  regioni.getX0() + ", " + regioni.getY0());
                                    System.out.println("Regioni w, h " + regioni.getWidth() + ", " + regioni.getHeight());
                                }
*/
                                if (eyetrackerRecord.getRightEyeX()*100 >= regioni.getX0() && eyetrackerRecord.getRightEyeX()*100 <= (regioni.getX0() + regioni.getWidth()) && eyetrackerRecord.getRightEyeY()*100 >= regioni.getY0() && eyetrackerRecord.getRightEyeY()*100 <= (regioni.getY0() + regioni.getHeight()))
                                {
                                    AnalizaPogleda analizaPogleda = new AnalizaPogleda(eyetrackerRecord.getRightEyeX(), eyetrackerRecord.getRightEyeY(), pitanjeTimestamp.getPitanje(), reseniTest.get(), regioni.getId(), eyetrackerRecord.getTimestamp());
                                    analizaPogleda = analizaPogledaRepository.save(analizaPogleda);
                                    nR++;
                                }
/*                              else
                                {
                                    System.out.println("Right Eye x " + eyetrackerRecord.getRightEyeX());
                                    System.out.println("Right Eye y " + eyetrackerRecord.getRightEyeY());
                                    System.out.println("Regioni Xo, Yo " +  regioni.getX0() + ", " + regioni.getY0());
                                    System.out.println("Regioni w, h " + regioni.getWidth() + ", " + regioni.getHeight());
                                }
*/
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Left Eye in region " + nL);
        System.out.println("Right Eye in region " + nR);
    }
    //Pitanje
    public List<AnalizaPogleda> getRegions(Long reseniTestId, Long pitanjeId)
    {
        analizeTest(reseniTestId);
        return analizaPogledaRepository.findByReseniTestIdAndPitanjeIdOrderByTimestamp(reseniTestId, pitanjeId);
    }

    public List<ReseniTest> getUradjene(User u) {
        return reseniTestRepository.findByUcenikId(u.getId());
    }

    public ReseniTest getReseniTest (Long reseniTestId)
    {
        return reseniTestRepository.findById(reseniTestId).get();
    }
}
