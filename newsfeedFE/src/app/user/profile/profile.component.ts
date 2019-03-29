import {Component, OnInit} from '@angular/core';
import {ProfileData, ProfileService} from "../profile.service";
import {MatDialog} from "@angular/material";
import {EditProfileComponent, ProfileDialogData} from "../edit-profile/edit-profile.component";


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
        }, error => {
          console.log("Error happened.");
          console.log(error);
        }
      )
  }

  editUserInfoDialog(): void {
    const dialogRef = this.dialog.open(EditProfileComponent, {
      width: '350px',
      data: this.profileData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.new_profile = result;
      this.new_profile.id = this.profileData.id;
      this.new_profile.firstName = this.profileData.firstName;
      this.new_profile.lastName = this.profileData.lastName;
      this.new_profile.email = this.profileData.email;
      this.new_profile.bio = this.profileData.bio;

      // @ts-ignore
      this.profileService.updateProfileInfo(this.new_profile).subscribe(res => this.profile = res);

    });
  }


  addUserBioDialog(): void {
  }


}
