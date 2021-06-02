import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";
import {AnalizaPogleda, JwtResponse, LoginRequest, Pitanje, ReseniTest, Test} from "../model";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TestoviService {

  private baseUrl = 'http://localhost:8080/test/';
  private fileUrl = 'http://localhost:8080/file/';


  constructor(private router: Router,
              private http: HttpClient) { }

  addQuestion(pitanje: string, odgovori: string, username: string): Observable<any>
  {
    let param = new HttpParams();

    param = param.append('pitanje',pitanje);
    param = param.append('odgovori',odgovori);
    param = param.append('username',username);

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

  getTest(testId: number, username: string): Observable<any>
  {
    let param = new HttpParams();

    param = param.append('testId',String(testId));
    param = param.append('username',username);

    return this.http.get<any>(this.baseUrl + 'getTest', {params : param});
  }

  reseniTest (object) : Observable<any>
  {
    return this.http.put<any>(this.baseUrl + 'reseniTest', object);
  }

  getTests(testId : number, username: string): Promise<ReseniTest[]>
  {
    let param = new HttpParams();

    param = param.append('testId', String(testId));
    param = param.append('username', username)

    return this.http.get<ReseniTest[]>(this.baseUrl + 'getTests', {params : param}).toPromise();
  }

  getAll(username: string): Promise<Test[]>
  {
    let param = new HttpParams();

    param = param.append('username', username)

    return this.http.get<Test[]>(this.baseUrl + 'getAll', {params : param}).toPromise();
  }

  updateBodove (object) : Observable<any>
  {
    return this.http.put<any>(this.baseUrl + 'updateBodove', object);
  }

  getRegions(reseniTestId : number, username: string): Promise<AnalizaPogleda[]>
  {
    let param = new HttpParams();

    param = param.append('reseniTestId', String(reseniTestId));
    param = param.append('username', username)

    return this.http.get<AnalizaPogleda[]>(this.baseUrl + 'getAnaliza', {params : param}).toPromise();
  }

  getAllUradjene(username: string): Promise<ReseniTest[]>
  {
    let param = new HttpParams();

    param = param.append('username', username);

    return this.http.get<ReseniTest[]>(this.baseUrl + 'getAllUradjene', {params : param}).toPromise();
  }

  getRecordsFile (reseniTest: number) : Observable<Blob>
  {
    let param = new HttpParams();

    param = param.append('reseniTestId', String(reseniTest));

    return this.http.get(this.fileUrl + 'exportFile', {params : param,
      responseType: 'blob'
    });
  }
}
