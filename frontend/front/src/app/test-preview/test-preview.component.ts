import { Component, OnInit } from '@angular/core';
import {Pitanje, Test, User} from "../model";
import {TestoviService} from "../services/testovi.service";
import {TokenService} from "../services/token.service";

@Component({
  selector: 'app-test-preview',
  templateUrl: './test-preview.component.html',
  styleUrls: ['./test-preview.component.css']
})
export class TestPreviewComponent implements OnInit {

  questions: Pitanje[];

  constructor(private testoviService: TestoviService,
              private tokenService: TokenService) { }

  ngOnInit(): void
  {
    this.questions = JSON.parse(localStorage.getItem('test'));

    localStorage.removeItem('test');

  }

  up(pitanje: Pitanje, ii: number)
  {
    if (ii > 0)
    {
      var pom = this.questions[ii-1];
      this.questions[ii] = pom;
      this.questions[ii-1] = pitanje;

      localStorage.setItem("test", JSON.stringify(this.questions));
      window.location.reload();
    }
  }

  down(pitanje: Pitanje, ii: number)
  {
    if (ii < this.questions.length - 1)
    {
      var pom = this.questions[ii+1];
      this.questions[ii] = pom;
      this.questions[ii+1] = pitanje;

      localStorage.setItem("test", JSON.stringify(this.questions));
      window.location.reload();
    }
  }

  createTest()
  {

    for(let i = 0; i < this.questions.length; i++)
    {
      this.questions[i].redniBroj = i + 1;
    }

    var test = new Test(this.questions);
    test.kreator = new User(this.tokenService.getUsername(), "", 0);

    console.log("TEST");
    console.log(test);

    this.testoviService.createTest(test).subscribe( data =>
    {
      console.log(data);
    })
  }
}
