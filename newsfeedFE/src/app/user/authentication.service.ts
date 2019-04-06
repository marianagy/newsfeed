import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {tap} from "rxjs/operators";
import {JwtHelperService} from '@auth0/angular-jwt';
import * as moment from 'moment';
import {now} from 'moment';

export interface UserLoginData {
  username: string,
  password: string
}

export interface UserData {
  email: string;
}

export interface TokenData {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  baseURL = 'http://localhost:8080/login';

  constructor(private http: HttpClient) {
  }

  private static setSession(authResult) {
    const helper = new JwtHelperService();


    const decodedToken = helper.decodeToken(authResult.token);


    const expiresAt = new Date(decodedToken.exp * 1000);

    localStorage.setItem('token', authResult.token);
    localStorage.setItem('username', decodedToken.username);
    localStorage.setItem('role', decodedToken.role);
    localStorage.setItem('issuer', decodedToken.iss);
    localStorage.setItem('expires_at', decodedToken.exp);

  }

  public getToken(): string {
    return localStorage.getItem('token');
  }

  validateUser(username: string, password: string) {

    let body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);


    console.log(body);
    return this.http.post<TokenData>(this.baseURL,
      body.toString(),
      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/x-www-form-urlencoded'}
        )
      }).pipe(
      tap(res => AuthenticationService.setSession(res))
    );
  }

  public isLoggedIn() {


    if (!localStorage['expires_at']) {
      return false;
    }
    return this.getExpiration().isAfter(now());
  }

  public getRoleOfUser() {
    let role = localStorage['role'];
    return JSON.parse(role);
  }

  public userHasPermission(permissionString) {
    let userRole = this.getRoleOfUser();
    for (let role of userRole) {
      for (let permission of role.permissions) {
        if (permission.type === permissionString) {
          return true;
        }
      }
    }
    return false;
  }

  public logout() {
    localStorage.clear();
  }

  getExpiration() {
    const time = localStorage['expires_at'];

    const correctSec = time * 1000;
    var expiresAt = new Date(correctSec);


    return moment(expiresAt);
  }


}
