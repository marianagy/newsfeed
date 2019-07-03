import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  baseURL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  getAllUsers() {
    return this.http.get(this.baseURL + '/users');
  }

  activateUser(email) {
    const body = new URLSearchParams();
    body.set('username', email);
    return this.http.post(this.baseURL + '/activate-user', body.toString(),
      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/x-www-form-urlencoded'}
        )
      });

  }

  deactivateUser(email) {
    const body = new URLSearchParams();
    body.set('username', email);
    return this.http.post(this.baseURL + '/deactivate-user', body.toString(),
      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/x-www-form-urlencoded'}
        )
      });
  }

  checkActive(username) {
    // const body = new URLSearchParams();
    // body.set('username', username);
    return this.http.get(this.baseURL + '/deactivate-user/' + username,
      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/x-www-form-urlencoded'}
        )
      });
  }
}
