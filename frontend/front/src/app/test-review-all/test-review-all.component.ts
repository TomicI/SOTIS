import { Component, OnInit } from '@angular/core';
import {TestoviService} from "../services/testovi.service";
import {TokenService} from "../services/token.service";
import {ReseniTest} from "../model";

@Component({
  selector: 'app-test-review-all',
  templateUrl: './test-review-all.component.html',
  styleUrls: ['./test-review-all.component.css']
})
export class TestReviewAllComponent implements OnInit {

  testId : number;
  reseniTestovi: ReseniTest[];

  constructor(private testoviService : TestoviService,
              private tokenService : TokenService) { }

  ngOnInit(): void
  {
    this.testId = JSON.parse(localStorage.getItem('testId'));

    localStorage.removeItem('testId');

    this.testoviService.getTests(this.testId, this.tokenService.getUsername()).then( data =>
      {
        console.log("datadata");
        console.log(data);
        this.reseniTestovi = data;

        if (this.reseniTestovi.length == 0)
        {
          alert("Ovaj test nije radjen! ");
          window.location.replace('/profile');
        }
      },
      error =>
      {
        alert("Ovaj test nije radjen! ");
        window.location.replace('/profile');
      });
  }

  pregled(ii: number)
  {
    localStorage.setItem('test', JSON.stringify(this.reseniTestovi[ii]));
    window.location.replace('/test_review');
  }

}
