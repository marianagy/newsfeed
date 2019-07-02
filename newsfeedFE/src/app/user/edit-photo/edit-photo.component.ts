import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ProfileComponent} from "../profile/profile.component";
import {ProfileService} from "../profile.service";
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
  selector: 'app-edit-photo',
  templateUrl: './edit-photo.component.html',
  styleUrls: ['./edit-photo.component.scss']
})
export class EditPhotoComponent implements OnInit {

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

  previewImage(event) {
    let reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.onload = () => {
        var img = new Image();
        img.width = 100;
        // @ts-ignore
        img.src = reader.result;
        document.getElementById("chosen_image").appendChild(img);
        this.data.photo = reader.result;
      };
      reader.readAsDataURL(file);


    }

  }

  // submitForm(){
  //   console.log(this.data);
  //   this.profileService.updateProfileInfo(this.data).subscribe(
  //     data => {
  //
  //       this.dialogRef.close();
  //     },
  //     err => {
  //       console.log(err);
  //     }
  //   )
  // }

}
