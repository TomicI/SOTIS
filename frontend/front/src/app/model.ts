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
}

export class Odgovor
{
  id: number;
  tekstOdgovora: string;
  tacan: boolean;
  brojBodova: number;
}
