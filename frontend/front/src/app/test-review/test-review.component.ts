import { Component, OnInit } from '@angular/core';
import {Odgovor, ReseniTest} from "../model";
import {TestoviService} from "../services/testovi.service";

@Component({
  selector: 'app-test-review',
  templateUrl: './test-review.component.html',
  styleUrls: ['./test-review.component.css']
})
export class TestReviewComponent implements OnInit {

  reseniTest : ReseniTest;
  brojBodova: number[];

  constructor(private testService : TestoviService) { }

  ngOnInit(): void
  {
    this.reseniTest = JSON.parse(localStorage.getItem('test'));

    localStorage.removeItem('test');

    this.brojBodova = new Array(this.reseniTest.test.pitanja.length) ;

    console.log("Broj pitanja " + this.reseniTest.test.pitanja.length);

    console.log("Broj odgovora " + this.reseniTest.odgovorSet.length);

    for(let i = 0; i < this.reseniTest.test.pitanja.length; i++)
    {
      for (let j = 0; j < this.reseniTest.odgovorSet.length; j++)
      {
        if (this.reseniTest.odgovorSet[i].pid == this.reseniTest.test.pitanja[i].id)
        {
          console.log("Broj bodova od " + i + " pitanja ");
          console.log(this.reseniTest.odgovorSet[i].brojBodova);
          this.brojBodova[i] = this.reseniTest.odgovorSet[i].brojBodova;
        }
      }
    }
  }

  getOdgovori(pitanjeId :number) : string
  {
    let ret = "";

    for (let i = 0; i < this.reseniTest.odgovorSet.length; i++)
    {
      if (this.reseniTest.odgovorSet[i].pid == pitanjeId)
      {
        ret += this.reseniTest.odgovorSet[i].tekstOdgovora;
      }
    }

    return ret;
  }

  getBodovi(pitanjeId :number) : number
  {
    let ret = 0;

    for (let i = 0; i < this.reseniTest.odgovorSet.length; i++)
    {
      if (this.reseniTest.odgovorSet[i].pid == pitanjeId)
      {
        ret += this.reseniTest.odgovorSet[i].brojBodova;
      }
    }

    return ret;
  }

  finish()
  {
    for (let i = 0; i < this.reseniTest.odgovorSet.length; i++)
    {
      for(let j = 0; j < this.brojBodova.length; j++)
      {
        if (this.reseniTest.odgovorSet[i].pid == this.reseniTest.test.pitanja[j].id)
        {
          this.reseniTest.odgovorSet[i].brojBodova = this.brojBodova[i];
        }
      }
    }

    this.testService.updateBodove(this.reseniTest).subscribe(data =>
    {
      console.log(data);
    })
  }
}
