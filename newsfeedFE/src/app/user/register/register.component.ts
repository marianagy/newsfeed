import {Component, OnInit} from '@angular/core';
import {RegistrationService, UserRegisterData} from "../registration.service";
import {FormControl, Validators} from "@angular/forms";
import {ErrorStateMatcher} from "@angular/material";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  userRegisterData: UserRegisterData;
  error: boolean;
  errorMessage: string;

  emailFormControl = new FormControl('', [
    Validators.email,
    Validators.required,
    this.validateEmail
  ]);

  passwordFormControl = new FormControl('', [
    Validators.required,
    this.validatePassword
  ]);
  passwordErrorMessage: string;
  emailErrorMessage: string;
  matcher = new ErrorStateMatcher();

  constructor(private registrationService: RegistrationService,
              private translate: TranslateService) {

    this.userRegisterData = {
      firstName: '',
      lastName: '',
      email: '',
      username: '',
      password: '',
      passwordConfirmation: ''
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

  validatePassword(control: FormControl) {
    const regex = new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*.-]).{6,}$');

    if (regex.test(control.value)) {
      return null;
    }
    return {
      passwordInvalid: {
        password: control.value
      }
    }
  }

  validateEmail(control: FormControl) {
    const regex = new RegExp('^[A-Za-z0-9._%+-]+@[a-zA-z]+.com$');
    if (regex.test(control.value)) {
      return null;
    }
    return {
      emaildomainerror: {
        email: control.value
      }
    }
  }

  getEmailErrorMessages() {

    this.emailFormControl.hasError('required') ? (this.emailErrorMessage = "Email is required") :
      this.emailFormControl.hasError('email') ? (this.emailErrorMessage = "Email is not valid") :
        '';
    return this.emailErrorMessage;

  }


  submitRegister() {
    this.registrationService.registerUser(
      this.userRegisterData.firstName,
      this.userRegisterData.lastName,
      this.userRegisterData.email,
      this.userRegisterData.username,
      this.userRegisterData.password
    ).subscribe(
      data => {
        console.log("Succes: " + data)
      }, error => {
        console.log(error)
      }
    )

  }

  areCorrectPasswords() {

    return this.userRegisterData.password === this.userRegisterData.passwordConfirmation;
  }

  getPasswordsNotEqualErrorMessage() {
    return "Passwords do not match";
  }

  getPasswordErrorMessage() {

    return "Password must have at least 6 characters, one lowercase letter, one uppercase letter, one numeric digit, and one special character.";
  }

  ngOnInit() {
  }

}
