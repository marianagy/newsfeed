import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ProfileComponent} from "../profile/profile.component";
import {ProfileService} from "../profile.service";

export interface ProfileDialogData {
  firstName: string;
  lastName: string;
  photo: any;
  email: string;
  bio: string;
}

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ProfileComponent>,
              private profileService: ProfileService,
              @Inject(MAT_DIALOG_DATA) public data: ProfileDialogData) {
  }

  ngOnInit() {
  }

}
