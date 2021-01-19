import { Component, OnInit } from '@angular/core';
import {Pitanje} from "../model";

@Component({
  selector: 'app-question-preview',
  templateUrl: './question-preview.component.html',
  styleUrls: ['./question-preview.component.css']
})
export class QuestionPreviewComponent implements OnInit {

  pitanje: Pitanje;

  constructor() { }

  ngOnInit(): void
  {
      this.pitanje = JSON.parse(localStorage.getItem('question'));
      console.log("Pitanje ");
      console.log(this.pitanje.tekstPitanja);
      localStorage.removeItem('question');
  }

}
