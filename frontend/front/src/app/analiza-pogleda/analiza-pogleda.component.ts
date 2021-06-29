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
      this.reseniTest.test.pitanja.sort((a,b) => a.redniBroj > b.redniBroj ? 1 : -1);
      console.log("RESENI TEST ")
      console.log(this.reseniTest);

//    localStorage.removeItem('test');


      if(JSON.parse(localStorage.getItem('brojPitanja')) != null)
      {
        this.brojPitanja = JSON.parse(localStorage.getItem('brojPitanja'));
      }

      this.testoviService.getRegions(this.reseniTest.id, this.tokenService.getUsername(), this.reseniTest.test.pitanja[this.brojPitanja].id).then( data => {
        console.log("datadata");
        console.log(data.length);
        this.koordinate = data;

        this.pitanje = this.reseniTest.test.pitanja[this.brojPitanja];

        if (this.koordinate.length == 0)
        {
          alert("Ne postoje zabelezeni podaci o pogledima na ovom pitanju! ");
        }
        else
        {
          console.log("ELSE")
          this.draw();
        }
      });

    }
/*
      this.testoviService.getRegions(this.reseniTest.id, this.tokenService.getUsername()).then( data =>
        {
          console.log("datadata");
          console.log(data.length);
          this.koordinate = data;

          if (this.koordinate.length == 0)
          {
            alert("Ne postoje zabelezeni podaci o pogledima! ");
            window.location.replace('/profile');
          }
          else
          {
            console.log("ELSE")
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
*/

    console.log("Pitanje");
    console.log(this.pitanje);
  }

  draw()
  {
    let currRId = -1;
    let lineX, lineY = 0;
    let regioniRedniBr = 1;
    for(let i=0; i<this.koordinate.length; i++)
    {
      if(this.pitanje.id == this.koordinate[i].pitanje.id) {
        if (currRId == -1)
        {
          currRId = this.koordinate[i].regionId;
          lineX = this.koordinate[i].x*100;
          lineY = this.koordinate[i].y*100;
        }

        this.ctx.beginPath();
        this.ctx.strokeStyle = "black";
        this.ctx.lineWidth = 2;
//      this.ctx.rect(this.koordinate[i].x * 100, this.koordinate[i].y * 100, 1, 1);
        this.ctx.arc(this.koordinate[i].x * 100, this.koordinate[i].y * 100, 10, 0, 2 * Math.PI);
        this.ctx.stroke();

        if(this.koordinate[i].regionId != currRId )
        {
          console.log("RAYLICIT JE " + currRId + " reg " + this.koordinate[i].regionId)
          this.ctx.beginPath();
          this.ctx.moveTo(lineX, lineY);
          this.ctx.lineTo(this.koordinate[i].x*100, this.koordinate[i].y*100);
          this.ctx.stroke();

          this.ctx.font = "50px Arial";
          this.ctx.fillStyle = "black";
          this.ctx.fillText(regioniRedniBr,(lineX+this.koordinate[i].x*100)/2,(lineY+this.koordinate[i].y*100)/2);
          regioniRedniBr++;

          currRId = this.koordinate[i].regionId;
          lineX = this.koordinate[i].x;
          lineY = this.koordinate[i].y;
        }
      }
    }
  }

  done()
  {

  }

  next()
  {
    if (this.brojPitanja < this.reseniTest.test.pitanja.length - 1)
    {
      console.log("NEXT")
      localStorage.setItem('koordinate', JSON.stringify(this.koordinate));
      localStorage.setItem('brojPitanja', JSON.stringify(this.brojPitanja + 1));
      window.location.reload();
    }
  }

  back()
  {
    if (this.brojPitanja > 0 )
    {
      console.log("BACK")
      localStorage.setItem('koordinate', JSON.stringify(this.koordinate));
      localStorage.setItem('brojPitanja', JSON.stringify(this.brojPitanja - 1));
      window.location.reload();
    }
  }
}
