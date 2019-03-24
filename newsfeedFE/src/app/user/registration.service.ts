import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserData} from "./authentication.service";

export interface UserRegisterData {
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password: string;
  passwordConfirmation: string;

}


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  baseURL = 'http://localhost:8080/register';

  constructor(private http: HttpClient) {
  }

  // are acces la baza de date, ca din postman

  registerUser(firstName: string,
               lastName: string,
               email: string,
               username: string,
               password: string
  ) {
    let body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);
    body.set('first_name', firstName);
    body.set('last_name', lastName);
    body.set('email', email);

    console.log(body);
    return this.http.post<UserData>(this.baseURL,
      body.toString(),
      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/x-www-form-urlencoded'}
        )
      })

  }


}
