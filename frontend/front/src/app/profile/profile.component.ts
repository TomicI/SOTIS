import { Component, OnInit } from '@angular/core';
import {TestoviService} from "../services/testovi.service";
import {AuthServiceService} from "../services/auth-service.service";
import {TokenService} from "../services/token.service";
import {User, Test} from "../model";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user : User;
  testovi : Test[];

  constructor(private testoviService : TestoviService,
              private authService: AuthServiceService,
              private tokenStorage: TokenService) { }

  ngOnInit(): void
  {

    this.authService.getCUser(this.tokenStorage.getUsername()).then( data =>
    {
      console.log("data");
      console.log(data);
      this.user = data;
    }, error =>
    {
      alert("Morate biti ulogovani!");
      window.location.replace('/sign_in');
    });

    this.testoviService.getAll(this.tokenStorage.getUsername()).then( data =>
    {
        console.log("datadata");
        console.log(data);
        this.testovi = data;
    });
  }

  createTest()
  {
    localStorage.setItem('userId', JSON.stringify(this.user.id));
    window.location.replace('/create_test');
  }

  pregled(testId: number)
  {
    localStorage.setItem('testId', JSON.stringify(testId));
    window.location.replace('/tests_review');
  }
}
