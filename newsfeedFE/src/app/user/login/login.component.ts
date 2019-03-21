import {Component, OnInit} from '@angular/core';
import {AuthenticationService, UserData, UserLoginData} from "../authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userLoginData: UserLoginData;
  userData: UserData;
  error: boolean;
  errorMessage: string;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
    this.userLoginData = {
      username: '',
      password: '',
    };
    this.userData = {
      email: '',

    };
    this.error = false;
    this.errorMessage = '';


  }

  displayError() {
    return this.error;
  }

  getMessage() {
    return this.errorMessage;
  }

  submitForm() {
    console.log("Component here...");
    this.authenticationService.validateUser(this.userLoginData.username, this.userLoginData.password)
      .subscribe(
        data => {
          this.error = false;
        },
        err => {
          this.error = true;
          console.log(err);
          if (err.valueOf().error.value == 'USER_DEACTIVATED') {
            this.errorMessage = "This user was deactivated";
          } else {
            this.errorMessage = "Username or password are incorrect."
          }
        }
      );
  }

  // isLoggedIn() {
  //   return this.authenticationService.isLoggedIn();
  // }
  //
  // logout() {
  //   this.authenticationService.logout();
  // }
  ngOnInit() {
  }

}
