import {Component, OnInit} from '@angular/core';
import {ProfileData, ProfileService} from "../profile.service";
import {MatDialog} from "@angular/material";
import {EditProfileComponent, ProfileDialogData} from "../edit-profile/edit-profile.component";
import {EditBioComponent} from "../edit-bio/edit-bio.component";
import {EditPhotoComponent} from "../edit-photo/edit-photo.component";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {


  profileData: ProfileData;
  private new_profile: ProfileDialogData;

  constructor(private profileService: ProfileService,
              public dialog: MatDialog,
  ) {
    this.profileData = {
      id: "",
      firstName: "",
      lastName: "",
      email: "",
      photo: "",
      bio: ""
    };
  }

  ngOnInit() {
    this.loadProfileData();
  }

  loadProfileData() {
    this.profileService.getProfileByUsername(localStorage.getItem("username"))
      .subscribe(
        data => {
          console.log("Everything ok.");
          console.log(data);
          this.profileData.id = data["id"];
          this.profileData.firstName = data["firstName"];
          this.profileData.lastName = data["lastName"];
          this.profileData.email = data["email"];
          this.profileData.bio = data["bio"];
          this.profileData.photo = data["photo"];
        }, error => {
          console.log("Error happened.");
          console.log(error);
        }
      )
  }

  editUserInfoDialog(): void {
    const dialogRef = this.dialog.open(EditProfileComponent, {
      width: '350px',
      data: {
        "id": this.profileData.id,
        "firstName": this.profileData.firstName,
        "lastName": this.profileData.lastName,
        "email": this.profileData.email,
        "bio": this.profileData.bio,
        "photo": this.profileData.photo,
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result);
      this.new_profile = result;
      this.profileData.firstName = this.new_profile.firstName;
      this.profileData.lastName = this.new_profile.lastName;
      this.profileData.email = this.new_profile.email;

      // @ts-ignore

    });
  }


  editUserBioDialog(): void {
    const dialogRef = this.dialog.open(EditBioComponent, {
      width: '350px',
      // autocompleteaza form-ul (si data), cu datele pe care le am din baza de date
      data: {
        "id": this.profileData.id,
        "firstName": this.profileData.firstName,
        "lastName": this.profileData.lastName,
        "email": this.profileData.email,
        "bio": this.profileData.bio,
        "photo": this.profileData.photo,
      }
    });

    // result va contine this.data
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result);
      this.new_profile = result;
      this.profileData.bio = this.new_profile.bio;

      // @ts-ignore

    });
  }

  editPhotoDialog() {
    const dialogRef = this.dialog.open(EditPhotoComponent, {
      width: '350px',
      data: {
        "id": this.profileData.id,
        "firstName": this.profileData.firstName,
        "lastName": this.profileData.lastName,
        "email": this.profileData.email,
        "bio": this.profileData.bio,
        "photo": this.profileData.photo,
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result);
      this.new_profile = result;
      if (this.new_profile.photo != undefined) {
        this.profileData.photo = this.new_profile.photo;
      }


      // @ts-ignore
      this.profileService.updateProfileInfo(this.new_profile).subscribe(res => this.profile = res);
    });
  }


}
