import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  baseURL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  // method used for request -> called in profile component
  getProfileByUsername(username) {
    return this.http.get(this.baseURL + '/current-profile/' + username);
  }

  updateProfileInfo(profile) {
    return this.http.put(this.baseURL + '/update-profile',
      profile,
      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/json'}
        )
      });
  }


}

