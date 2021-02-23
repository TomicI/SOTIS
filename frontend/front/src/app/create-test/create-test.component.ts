import { Component, OnInit } from '@angular/core';
import {TestoviService} from "../services/testovi.service";
import {Pitanje} from "../model";

@Component({
  selector: 'app-create-test',
  templateUrl: './create-test.component.html',
  styleUrls: ['./create-test.component.css']
})
export class CreateTestComponent implements OnInit {

  userId : number = 0;
  questions : Pitanje[];
  questionsAdded : Pitanje[];
  isChecked: boolean[];

  constructor(private testoviService : TestoviService) { }

  ngOnInit(): void
  {
    this.userId = JSON.parse(localStorage.getItem('userId'));

    localStorage.removeItem('userId');

    this.testoviService.getQuestion(this.userId).then( data =>
    {
      console.log("datadata");
      console.log(data);
      this.questions = data;
      this.isChecked = new Array(this.questions.length).fill(false);
    },
      error =>
      {
        alert("Dodajte pitanje");
        window.location.replace('/addQuestion');
      });

    this.questionsAdded = [];
  }

  dodaj(pitanje: Pitanje, event: any)
  {
    console.log(pitanje.id);
    console.log(event);

    if (event == 'A')
    {
      this.questionsAdded.push(pitanje);
    }
    else
    {
      const index = this.questionsAdded.indexOf(pitanje);
      this.questionsAdded.splice(index, 1);
    }
  }

  createTest()
  {
    localStorage.setItem("test", JSON.stringify(this.questionsAdded));
    window.location.replace('/test_preview');
  }
}
