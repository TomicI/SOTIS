import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";
import {JwtResponse, LoginRequest, Pitanje} from "../model";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TestoviService {

  private baseUrl = 'http://localhost:8080/test/';

  constructor(private router: Router,
              private http: HttpClient) { }

  addQuestion(pitanje: string, odgovori: string): Observable<any>
  {
    let param = new HttpParams();

    param = param.append('pitanje',pitanje);
    param = param.append('odgovori',odgovori);

    return this.http.get<any>(this.baseUrl + 'addQuestion', {params : param});
  }

  addRegions(object): Observable<any>
  {
    return this.http.post<any>(this.baseUrl + 'addRegions', object);
  }

  getQuestion(creatorId : number): Promise<Pitanje[]>
  {
    let param = new HttpParams();

    param = param.append('creatorId', String(creatorId));

    return this.http.get<Pitanje[]>(this.baseUrl + 'getQuestions', {params : param}).toPromise();
  }

  createTest(object): Observable<any>
  {
    return this.http.post<any>(this.baseUrl + 'createTest', object);
  }
}
