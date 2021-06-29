import { Component, OnInit } from '@angular/core';
import {Odgovor, Pitanje, PitanjeTimestamp, ReseniTest, Test} from "../model";
import {ActivatedRoute} from "@angular/router";
import {TestoviService} from "../services/testovi.service";
import {TokenService} from "../services/token.service";
import {Timestamp} from "rxjs/internal-compatibility";

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  test: Test;
  question: Pitanje;
  ii: number = 0;
  odgovoriI: number = 0;
  isChecked: boolean[];
  tekstOdgovora = "";
  nemaOdg = false;
  reseniTest: ReseniTest;

  constructor(private route: ActivatedRoute,
              private testService: TestoviService,
              private tokenService: TokenService)
  {
  }

  ngOnInit(): void
  {
    if (this.tokenService.getUsername() == null)
    {
      alert("Morate biti ulogovani!");
      window.location.replace('/sign_in');
    }

    if (localStorage.getItem('test'))
    {
      console.log(localStorage.getItem('test'));

      this.reseniTest = JSON.parse(localStorage.getItem('test'));
      this.reseniTest.test.pitanja.sort((a,b) => a.redniBroj > b.redniBroj ? 1 : -1);
      this.test = this.reseniTest.test;
      this.ii = JSON.parse(localStorage.getItem('question'));

      console.log(`reseni odgovri ` );
      console.log(this.reseniTest.odgovorSet);

      console.log(`Ovdeeeeeeee` + this.ii);


      if (this.reseniTest.pitanjeRecords == null)
      {
        this.reseniTest.pitanjeRecords = new Array();
        var pitanjeTimestamp: PitanjeTimestamp = new PitanjeTimestamp(Date.now(), this.question);

        this.reseniTest.pitanjeRecords.push(pitanjeTimestamp);
      }
    }
    else
    {
      this.route.params.subscribe
           ( params =>  { const id = params['id'];
           if (id != null) {
             console.log(`Test with id '${id}' `);
             this.testService.getTest(id, this.tokenService.getUsername()).subscribe(Rtest =>
             {
               if (Rtest)
               {
                 this.reseniTest = Rtest;
                 this.reseniTest.odgovorSet = [];
                 this.test = this.reseniTest.test;

                 this.ii = 0;
                 var pitanje: Pitanje = new Pitanje();
                 pitanje.tekstPitanja = "Odgovori";
                 this.test.pitanja.push(pitanje);
                 this.reseniTest.test = this.test;

                 localStorage.setItem("test", JSON.stringify(this.reseniTest));
                 localStorage.setItem("question", JSON.stringify(0));

                 window.location.reload();
               }
               else
               {
                 console.log(`Test with id '${id}' not found `);
               }

             },
               error => {
                 console.log(error);
                 alert("Ovaj test je vec radjen!");
                 localStorage.clear();
                 window.location.replace('/profile');
               });
           }
         });
    }

    this.question = this.reseniTest.test.pitanja[this.ii];

    this.odgovoriI = this.question.odgovori.length;

    if (this.question.odgovori != null && this.question.odgovori.length <= 1)
    {
      this.nemaOdg = true;

      if (this.question.odgovori.length == 1)
      {
        this.tekstOdgovora = this.question.odgovori[0].tekstOdgovora;
        this.test.pitanja[this.ii].odgovori = this.question.odgovori;
      }
      else
      {
        this.reseniTest.odgovorSet = new Array();
        this.test.pitanja[this.ii].odgovori = new Array();

      }
    }
    else
    {
      this.isChecked = new Array(this.question.odgovori.length).fill(false);
      for (let i = 0; i < this.test.pitanja[this.ii].odgovori.length; i++)
      {
        for (let j = 0; j < this.reseniTest.odgovorSet.length; j++)
        {
          if (this.reseniTest.odgovorSet[j].id == this.test.pitanja[this.ii].odgovori[i].id)
          {
            console.log("true");
            this.isChecked[i] = true;
          }
        }
      }
    }
  }

  next()
  {
    if (this.ii < this.test.pitanja.length - 2)
    {

      this.saveTimestamp();
      this.check();

      localStorage.setItem("question", JSON.stringify(this.ii + 1));
      window.location.reload();
    }
  }

  back()
  {
    if (this.ii > 0 )
    {

      this.saveTimestamp();
      this.check();

      localStorage.setItem("question", JSON.stringify(this.ii - 1));
      window.location.reload();
    }
  }

  saveTimestamp()
  {

    if (this.reseniTest.pitanjeRecords != null && this.reseniTest.pitanjeRecords.length > 0 )
    {
      this.reseniTest.pitanjeRecords[this.reseniTest.pitanjeRecords.length-1].timestampEnd = Date.now();

      console.log("prethodni ");
      console.log(this.reseniTest.pitanjeRecords[this.reseniTest.pitanjeRecords.length-1]);
    }

    var pitanjeTimestamp: PitanjeTimestamp = new PitanjeTimestamp(Date.now(), this.question);

    this.reseniTest.pitanjeRecords.push(pitanjeTimestamp);

  }

  check()
  {
    if (this.question.odgovori == null || this.question.odgovori.length <= 1)
    {
      console.log("tekst odgovora");
      console.log(this.tekstOdgovora);

      var odg: Odgovor = new Odgovor();
      odg.tekstOdgovora = this.tekstOdgovora;

      if (this.question.odgovori.length == 1)
      {
        this.test.pitanja[this.ii].odgovori[0].tekstOdgovora = odg.tekstOdgovora;
      }
      else
      {
        this.test.pitanja[this.ii].odgovori.push(odg);
      }
    }
    this.reseniTest.test = this.test;
    localStorage.setItem("test", JSON.stringify(this.reseniTest));
  }

  finish()
  {
    this.saveTimestamp();
    this.check();

    for (let i = 0; i < this.test.pitanja.length; i++)
    {
      if (this.test.pitanja[i].odgovori != null && this.test.pitanja[i].odgovori.length <= 1)
      {
        console.log("PItanje");
        console.log(this.test.pitanja[i].tekstPitanja);
        var odg: Odgovor = new Odgovor();
        odg = this.test.pitanja[i].odgovori[0];
        odg.id = 0;
        odg.brojBodova = this.test.pitanja[i].id;
        this.reseniTest.odgovorSet.push(odg);
      }
    }

    this.testService.reseniTest(this.reseniTest).subscribe( data =>
    {
      console.log(data);
      localStorage.removeItem("test");
      localStorage.removeItem("question");
    })
  }

  dodaj(i: number, odgovor : Odgovor, event: any)
  {
    console.log(this.question.tekstPitanja);
    console.log(event);

    if (event == 'A')
    {
      this.reseniTest.odgovorSet.push(odgovor);
      if (this.test.pitanja[this.test.pitanja.length-1].odgovori == undefined)
        this.test.pitanja[this.test.pitanja.length-1].odgovori = [];

      this.test.pitanja[this.test.pitanja.length-1].odgovori.push(odgovor);

      this.isChecked[i] = true;

      console.log(this.reseniTest.odgovorSet);
    }
    else
    {
      const index = this.test.pitanja[this.test.pitanja.length-1].odgovori.indexOf(odgovor);
      this.test.pitanja[this.test.pitanja.length-1].odgovori.splice(index, 1);

      const index1 = this.reseniTest.odgovorSet.indexOf(odgovor);
      this.reseniTest.odgovorSet.splice(index1, 1);

      this.isChecked[i] = false;

      console.log(this.reseniTest.odgovorSet);
    }
  }
}
