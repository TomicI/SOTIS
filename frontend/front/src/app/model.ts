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
