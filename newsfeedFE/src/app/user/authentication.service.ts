import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {tap} from "rxjs/operators";


export interface UserLoginData {
  username: string;
  password: string;
}

export interface UserData {
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  baseURL = 'http://localhost:8080/login';

  constructor(private http: HttpClient) {
  }

  // public getToken(): string {
  //   return localStorage.getItem('token');
  // }

  validateUser(username: string, password: string) {

    let body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);


    console.log(body);
    return this.http.post<UserData>(this.baseURL,
      body.toString(),
      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/x-www-form-urlencoded'}
        )
      }).pipe(
      tap(res => this.setSession(res))
    );
  }

  private setSession(authResult) {

    localStorage.setItem('username', authResult.username);
  }

  // public isLoggedIn() {
  //
  //
  //   if (!localStorage['expires_at']) {
  //     return false;
  //   }
  //   return this.getExpiration().isAfter(now());
  // }
  //
  // public getRolesOfUser() {
  //   let roles = localStorage['roles'];
  //   return JSON.parse(roles);
  // }
  //
  // public userHasPermission(permissionString) {
  //   let userRoles = this.getRolesOfUser();
  //   for (let role of userRoles) {
  //     for (let permission of role.permissions) {
  //       if (permission.type === permissionString) {
  //         return true;
  //       }
  //     }
  //   }
  //   return false;
  // }

  // public logout() {
  //   localStorage.removeItem('id_token');
  //   localStorage.removeItem('expires_at');
  //   localStorage.removeItem('email');
  //   localStorage.removeItem('roles');
  // }
  //
  // getExpiration() {
  //   const time = localStorage['expires_at'];
  //
  //   const correctSec = time * 1000;
  //   var expiresAt = new Date(correctSec);
  //
  //
  //   return moment(expiresAt);
  // }
}
