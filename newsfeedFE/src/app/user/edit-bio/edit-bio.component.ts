import {Component, Inject, OnInit} from '@angular/core';
import {ProfileService} from "../profile.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ProfileComponent} from "../profile/profile.component";
import {TranslateService} from "@ngx-translate/core";

export interface ProfileDialogData {
  id: any;
  firstName: string;
  lastName: string;
  photo: any;
  email: string;
  bio: string;
}

@Component({
  selector: 'app-edit-bio',
  templateUrl: './edit-bio.component.html',
  styleUrls: ['./edit-bio.component.scss']
})
export class EditBioComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ProfileComponent>,
              private profileService: ProfileService,
              private translate: TranslateService,
              @Inject(MAT_DIALOG_DATA) public data: ProfileDialogData) {
  }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close(this.data);
  }

  submitForm() {
    console.log(this.data);
    this.profileService.updateProfileInfo(this.data).subscribe(
      data => {

        this.dialogRef.close(this.data);
      },
      err => {
        console.log(err);
      }
    )
  }

}
