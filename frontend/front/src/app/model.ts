import {Timestamp} from "rxjs";

export class LoginRequest
{
  username: string;
  password: string;

  constructor(username: string, password: string)
  {
    this.username = username;
    this.password = password;
  }

}

export class JwtResponse
{
  accessToken: string;
  type: string;
  username: string;
  authorities: string[];
}

export class Pitanje
{
  id: number;
  redniBroj: number;
  tekstPitanja: string;
  odgovori: Odgovor[];
  regioni: Regioni[];
}

export class Odgovor
{
  id: number;
  tekstOdgovora: string;
  tacan: boolean;
  brojBodova: number;
  pid : number;
}

export class Regioni
{
  id: number;
  x0: number;
  y0: number;
  width: number;
  height: number;
  pitanje: Pitanje;

  constructor(x0: number, y0: number, width: number, height: number)
  {
    this.x0 = x0;
    this.y0 = y0;
    this.width = width;
    this.height = height;
  }
}

export class User
{
  username: string;
  password: string;
  id: number;

  constructor(username: string, password: string, id: number)
  {
    this.username = username;
    this.password = password;
    this.id = id;
  }

}

export class Test
{
  id : number;
  pitanja: Pitanje[];
  kreator: User;

  constructor(pitanja: Pitanje[])
  {
    this.pitanja = pitanja;
  }
}

export class ReseniTest
{
  id: number;
  brojOstvarenihBodova: number;
  test: Test;
  ucenik: User;
  odgovorSet: Odgovor[];
  pitanjeRecords: PitanjeTimestamp[];

  constructor(test: Test, ucenik: User)
  {
    this.test = test;
    this.ucenik = ucenik;
  }
}

export class PitanjeTimestamp
{
  timestampStart: number;
  timestampEnd: number;
  pitanje: Pitanje;

  constructor(timestampStart: number, pitanje: Pitanje)
  {
    this.timestampStart = timestampStart;
    this.pitanje = pitanje;
  }
}

export class AnalizaPogleda
{
  x: number;
  y: number;
  pitanje: Pitanje;
  reseniTest: ReseniTest;
  regionId: number;

  constructor() { }
}
