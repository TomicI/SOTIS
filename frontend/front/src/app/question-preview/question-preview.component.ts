import { Component, OnInit } from '@angular/core';
import {Pitanje, Regioni} from "../model";
import { TestoviService} from "../services/testovi.service";

@Component({
  selector: 'app-question-preview',
  templateUrl: './question-preview.component.html',
  styleUrls: ['./question-preview.component.css']
})
export class QuestionPreviewComponent implements OnInit {

  pitanje: Pitanje;

  //Canvas
  canvas;
  ctx;
  //Variables
  canvasx;
  canvasy;
  last_mousex = 0;
  last_mousey = 0
  mousex = 0;
  mousey = 0
  mousedown = false;

  width;
  height;
  widthW = window.innerWidth;
  heightW = window.innerHeight;
  drawItems = [];

  constructor(private testoviService: TestoviService)
  {
  }

  ngOnInit(): void {
    this.pitanje = JSON.parse(localStorage.getItem('question'));
    console.log("Pitanje ");
    console.log(this.pitanje.tekstPitanja);
    localStorage.removeItem('question');

    this.canvas = document.getElementById('canvas');
    this.ctx = this.canvas.getContext('2d');

    //Variables
    this.canvasx = this.canvas.offsetLeft;
    this.canvasy = this.canvas.offsetTop;
    console.log("offset " + this.canvas.offsetLeft + " " + this.canvas.offsetTop )
  }

  //Mousedown

  onMouseDown(clientX: number, clientY: number) {
    console.log("X, Y");
    console.log(clientX);
    console.log(clientY);

    console.log("can X, Y");
    console.log(this.canvasx);
    console.log(this.canvasy);

    this.last_mousex = clientX - this.canvasx;
    this.last_mousey = clientY - this.canvasy;
    this.mousedown = true;
  }

  onMouseUp(e: any) {
    this.mousedown = false;

    console.log('END w/h: '+this.width+', '+this.height);
    console.log('END LAST: '+this.last_mousex+', '+this.last_mousey);

    this.drawItems.push(
      new Regioni(this.last_mousex, this.last_mousey, this.width, this.height)
/*      x0: this.last_mousex,
      x1: this.width,
      y0: this.last_mousey,
      y1: this.height */
    );
  }

  onMouseMove(clientX: number, clientY: number)
  {
    this.mousex = clientX - this.canvasx;
    this.mousey = clientY - this.canvasy;
    if(this.mousedown) {
      this.ctx.clearRect(0,0,this.canvas.width,this.canvas.height); //clear canvas

      console.log("DI");
      console.log(this.drawItems.length);

      for(let i=0; i<this.drawItems.length; i++) {
        this.ctx.beginPath();
        this.ctx.strokeStyle = "black";
        this.ctx.lineWidth = 2
        this.ctx.rect(this.drawItems[i].x0, this.drawItems[i].y0, this.drawItems[i].width, this.drawItems[i].height);
        this.ctx.stroke();
        console.log("x0 " + this.drawItems[i].x0 + " width " + this.drawItems[i].width + " y0 " + this.drawItems[i].y0 + " height " + this.drawItems[i].height);
      }

      this.width = this.mousex-this.last_mousex;
      this.height = this.mousey-this.last_mousey;
      this.ctx.beginPath();
      this.ctx.rect(this.last_mousex,this.last_mousey,this.width,this.height);
      this.ctx.stroke();

      console.log('current: '+this.mousex+', '+this.mousey+'<br/>last: '+this.last_mousex+', '+this.last_mousey+'<br/>mousedown: '+this.mousedown);
      console.log('w/h: '+this.width+', '+this.height);
    }
  }

  done()
  {
    this.pitanje.regioni = this.drawItems;

    this.testoviService.addRegions(this.pitanje).subscribe(data =>
    {
      console.log("data ");
      console.log(data);
    });
  }
}
