import { Component, OnInit } from '@angular/core';
import {AnalizaPogleda, Pitanje, ReseniTest} from "../model";
import {TestoviService} from "../services/testovi.service";
import {TokenService} from "../services/token.service";

@Component({
  selector: 'app-analiza-pogleda',
  templateUrl: './analiza-pogleda.component.html',
  styleUrls: ['./analiza-pogleda.component.css']
})
export class AnalizaPogledaComponent implements OnInit {

  //Canvas
  canvas;
  ctx;

  reseniTest: ReseniTest;
  koordinate: AnalizaPogleda[];

  widthW = window.innerWidth;
  heightW = window.innerHeight;

  brojPitanja: number = 0;

  pitanje: Pitanje;

  constructor(private testoviService: TestoviService, private tokenService: TokenService) { }

  ngOnInit(): void
  {
    this.canvas = document.getElementById('canvas');
    this.ctx = this.canvas.getContext('2d');


    if(JSON.parse(localStorage.getItem('test')) != null)
    {
      this.reseniTest = JSON.parse(localStorage.getItem('test'));
      localStorage.removeItem('test');

      this.testoviService.getRegions(this.reseniTest.id, this.tokenService.getUsername()).then( data =>
        {
          console.log("datadata");
          console.log(data);
          this.koordinate = data;

          if (this.koordinate.length == 0)
          {
            alert("Ne postoje zabelezeni podaci o pogledima! ");
            window.location.replace('/profile');
          }
          else
          {
            this.pitanje = this.koordinate[0].reseniTest.test.pitanja[this.brojPitanja];
            this.draw();
          }
        },
        error =>
        {
          alert("Doslo je do greske. Pokusajte ponovo. ");
          window.location.replace('/profile');
        });
    }
    else
    {
      this.koordinate = JSON.parse(localStorage.getItem('koordinate'));
      this.brojPitanja = JSON.parse(localStorage.getItem('brojPitanja'));

      this.pitanje = this.koordinate[0].reseniTest.test.pitanja[this.brojPitanja];

      this.draw();

    }

    console.log("Pitanje");
    console.log(this.pitanje);
  }

  draw()
  {
    for(let i=0; i<this.koordinate.length; i++)
    {
      if(this.pitanje.id == this.koordinate[i].pitanje.id)
      {
        this.ctx.beginPath();
        this.ctx.strokeStyle = "black";
        this.ctx.lineWidth = 2;
        this.ctx.rect(this.koordinate[i].x, this.koordinate[i].y, 1, 1);
        this.ctx.stroke();
        console.log("x " + this.koordinate[i].x + " y " + this.koordinate[i].y );
      }
    }
  }

  done()
  {

  }

  next()
  {
    if (this.brojPitanja < this.koordinate[0].reseniTest.test.pitanja.length - 1)
    {
      localStorage.setItem('koordinate', JSON.stringify(this.koordinate));
      localStorage.setItem('brojPitanja', JSON.stringify(this.brojPitanja + 1));
      window.location.reload();
    }
  }

  back()
  {
    if (this.brojPitanja > 0 )
    {
      localStorage.setItem('koordinate', JSON.stringify(this.koordinate));
      localStorage.setItem('brojPitanja', JSON.stringify(this.brojPitanja - 1));
      window.location.reload();
    }
  }
}
