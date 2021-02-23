import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from '../model';
import { AuthServiceService } from '../services/auth-service.service';
import { TokenService } from '../services/token.service';

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
  private loginInfo: LoginRequest;
  loginForm: FormGroup;

  constructor(private route: Router,
              private formBuilder: FormBuilder,
              private authService: AuthServiceService,
              private tokenStorage: TokenService) { }

  ngOnInit(): void
  {

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      window.location.replace('/profile');
    } else
    {
      this.loginForm = this.formBuilder.group(
        {
          username: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_ ]*')]],
          password: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_!?*#/]*')]]
        });
    }

  }

  // tslint:disable-next-line:typedef
  onSubmit(){

    console.log(this.loginForm);

    this.loginInfo = new LoginRequest(
      this.loginForm.get('username').value,
      this.loginForm.get('password').value);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        console.log("TOKEN ");
        console.log(data.accessToken);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
//      this.roles = this.tokenStorage.getAuthorities();
        window.location.replace('/profile');
      },
      error => {
        console.log(error);
        this.isLoginFailed = true;
      }
    );
  }

}
