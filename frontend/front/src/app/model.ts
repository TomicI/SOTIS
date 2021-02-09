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
