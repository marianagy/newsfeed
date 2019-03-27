import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";


export interface ProfileData {
  firstName: string;
  lastName: string;
  email: string;
  photo: any;
  bio: string;

}

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  baseURL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  // method used foe request -> called in profile component
  getProfileByUsername(username) {
    return this.http.get(this.baseURL + '/current-profile/' + username);
  }

}

