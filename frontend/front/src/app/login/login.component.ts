import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { UserLogin } from '../model';
import { AuthServiceService } from '../services/auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  buttonName = 'Log in';
  form: any = {};
  loginDiv = true;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: UserLogin;
  loginForm: FormGroup;

  constructor(private route: Router,
              private formBuilder: FormBuilder,
              private authService: AuthServiceService) { }

  ngOnInit(): void {

    this.loginForm = this.formBuilder.group({
        username: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_ ]*')]],
        password: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_!?*#/]*')]]
     });
  }

  // tslint:disable-next-line:typedef
  onSubmit(){

      console.log(this.loginForm);

      this.loginInfo = new UserLogin(
        this.loginForm.get('username').value,
        this.loginForm.get('password').value);

      this.authService.login(this.loginInfo);

  }

}
