import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TestoviService} from "../services/testovi.service";
import {TokenService} from "../services/token.service";

@Component({
  selector: 'app-add-question-form',
  templateUrl: './add-question-form.component.html',
  styleUrls: ['./add-question-form.component.css']
})
export class AddQuestionFormComponent implements OnInit {

  addQuestion: FormGroup;

  constructor(private route: Router,
              private formBuilder: FormBuilder,
              private testoviService: TestoviService,
              private tokenService: TokenService) { }

  ngOnInit(): void {
    this.addQuestion = this.formBuilder.group({
      question: [''],
      answer: ['']
    });
  }

  onSubmit(){

    console.log(this.addQuestion);

    this.testoviService.addQuestion(this.addQuestion.get('question').value, this.addQuestion.get('answer').value, this.tokenService.getUsername()).subscribe(
      data => {

        console.log("DATA ");
        console.log(data);

        localStorage.setItem('question', JSON.stringify(data));
        window.location.replace('/setRegionsInQuestion');
      },
      error => {
        console.log("ERROR ");
        console.log(error);
      }
    );

  }

}
