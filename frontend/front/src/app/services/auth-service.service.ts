import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private baseUrl = 'http://localhost:8080/';

  constructor(private router: Router,
              private http: HttpClient) { }


  // tslint:disable-next-line:typedef
  login(userLogin)
  {
    const params = new URLSearchParams();
    params.append('username', userLogin.username);
    params.append('password', userLogin.password);
    params.append('grant_type', 'password');

    const body = new HttpParams()
    .set('username', userLogin.username)
    .set('password', userLogin.password)
    .set('grant_type', 'password');

    const headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', Authorization: 'Basic ' + btoa('oauthorize:securitypass')});


    const options = {headers};

    return this.http.post(this.baseUrl + 'uua/oauth/token', body, options).subscribe(data =>
    {
      console.log(data);
    }, err => {
      console.log(err);
    });

  }
}
