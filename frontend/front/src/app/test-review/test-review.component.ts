import { Component, OnInit } from '@angular/core';
import {Odgovor, ReseniTest} from "../model";
import {TestoviService} from "../services/testovi.service";

@Component({
  selector: 'app-test-review',
  templateUrl: './test-review.component.html',
  styleUrls: ['./test-review.component.css']
})
export class TestReviewComponent implements OnInit {

  test : ReseniTest;
  brojBodova: number[];

  constructor(private testService : TestoviService) { }

  ngOnInit(): void
  {
    this.test = JSON.parse(localStorage.getItem('test'));

    localStorage.removeItem('test');

    this.brojBodova = new Array(this.test.test.pitanja.length) ;

    console.log("Broj pitanja " + this.test.test.pitanja.length);

    console.log("Broj odgovora " + this.test.odgovorSet.length);

    for(let i = 0; i < this.test.test.pitanja.length; i++)
    {
      for (let j = 0; j < this.test.odgovorSet.length; j++)
      {
        if (this.test.odgovorSet[i].pid == this.test.test.pitanja[i].id)
        {
          console.log("Broj bodova od " + i + " pitanja ");
          console.log(this.test.odgovorSet[i].brojBodova);
          this.brojBodova[i] = this.test.odgovorSet[i].brojBodova;
        }
      }
    }
  }

  getOdgovori(pitanjeId :number) : string
  {
    let ret = "";

    for (let i = 0; i < this.test.odgovorSet.length; i++)
    {
      if (this.test.odgovorSet[i].pid == pitanjeId)
      {
        ret += this.test.odgovorSet[i].tekstOdgovora;
      }
    }

    return ret;
  }

  getBodovi(pitanjeId :number) : number
  {
    let ret = 0;

    for (let i = 0; i < this.test.odgovorSet.length; i++)
    {
      if (this.test.odgovorSet[i].pid == pitanjeId)
      {
        ret += this.test.odgovorSet[i].brojBodova;
      }
    }

    return ret;
  }

  finish()
  {
    for (let i = 0; i < this.test.odgovorSet.length; i++)
    {
      for(let j = 0; j < this.brojBodova.length; j++)
      {
        if (this.test.odgovorSet[i].pid == this.test.test.pitanja[j].id)
        {
          this.test.odgovorSet[i].brojBodova = this.brojBodova[i];
        }
      }
    }

    this.testService.updateBodove(this.test).subscribe( data =>
    {
      console.log(data);
    })
  }
}
