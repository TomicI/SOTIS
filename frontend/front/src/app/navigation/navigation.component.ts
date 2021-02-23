import { Component, OnInit } from '@angular/core';
import {TokenService} from "../services/token.service";
import {AuthServiceService} from "../services/auth-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {


  buttonName = 'Sign up';

  loginDiv = true;
  signupDiv = false;
//isAdmin = false;


  isLoggedIn = false;

//roles: string[] = [];
//adminRoles: string[] = ['ROLE_ADMIN_RENT','ROLE_ADMIN_AVIO','ROLE_ADMIN_HOTEL'];

  name = 'Log in';

  username = '';

  constructor(private tokenStorage: TokenService,
              private authService: AuthServiceService,
              private router: Router) { }
  ngOnInit() {

    if (this.tokenStorage.getToken()) {

      this.isLoggedIn = true;
//    this.roles = this.tokenStorage.getAuthorities();
      this.username = this.tokenStorage.getUsername();

    }

  }
/*
  clickEvent() {

    this.loginDiv = !this.loginDiv;

    if (this.loginDiv) {
      this.name = 'Log in';
    } else {
      this.name = 'Sign up';
      this.buttonName = 'Login';
    }


  }
*/
  open() {
    if (this.isLoggedIn) {
      this.tokenStorage.signOut();
      window.location.replace('/sign_in');
    } else {
      window.location.replace('/sign_in');
    }
  }

}
