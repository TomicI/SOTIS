import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {JwtResponse, LoginRequest, Pitanje, User} from "../model";
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

  getCUser(username: string): Promise<User>
  {
    let param = new HttpParams();

    param = param.append('username', username);

    return this.http.get<User>(this.baseUrl + 'getCUser', {params : param}).toPromise();
  }
}
