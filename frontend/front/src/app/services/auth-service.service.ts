import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import * as jwt from 'jsonwebtoken';
import {JwtResponse, LoginRequest} from "../model";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private baseUrl = 'http://localhost:8080/users/';

  constructor(private router: Router,
              private http: HttpClient) { }


  // tslint:disable-next-line:typedef


  attemptAuth(credentials: LoginRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.baseUrl + 'signin', credentials, httpOptions);
  }

/*  signUp(user: User): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.baseUrl + 'signup', user, httpOptions);
  }
*/
}
