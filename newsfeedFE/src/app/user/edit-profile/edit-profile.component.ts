import {Component, Inject, OnInit} from '@angular/core';
import {ErrorStateMatcher, MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ProfileComponent} from "../profile/profile.component";
import {ProfileService} from "../profile.service";
import {FormControl, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {TranslateService} from "@ngx-translate/core";

export interface ProfileDialogData {
  id: any;
  firstName: string;
  lastName: string;
  photo: any;
  email: string;
  bio: string;
}

export class MyErrorStateMatcher implements ErrorStateMatcher {

  constructor() {
  }

  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent implements OnInit {


  matcher = new MyErrorStateMatcher();
  emailFormControl = new FormControl('', [
    Validators.email,
    Validators.required,
    this.validateEmail
  ]);

  constructor(public dialogRef: MatDialogRef<ProfileComponent>,
              private profileService: ProfileService,
              private translate: TranslateService,
              @Inject(MAT_DIALOG_DATA) public data: ProfileDialogData
  ) {


  }

  validateEmail(control: FormControl) {
    const regex = new RegExp('^[A-Za-z0-9._%+-]+@[a-z0-9]+.[a-z]+$');
    if (regex.test(control.value)) {
      return null;
    }
    return {
      emaildomainerror: {
        emai: control.value
      }
    }
  }

  onNoClick(): void {
    console.log("ldguwcfevbsdcakmx");
    this.dialogRef.close(this.data);
  }

  ngOnInit() {

  }


  submitForm() {
    console.log(this.data);
    this.profileService.updateProfileInfo(this.data).subscribe(
      data => {
        if (this.data.firstName.trim() != "" && this.data.lastName.trim() != "" && this.data.email.trim() != "") {
          this.dialogRef.close(this.data);
        } else {
          console.log("Error");
        }

      },
      err => {
        console.log(err);
      }
    )
  }



}
