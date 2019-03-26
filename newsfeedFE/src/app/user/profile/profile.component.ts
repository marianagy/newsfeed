import {Component, OnInit} from '@angular/core';
import {ProfileData, ProfileService} from "../profile.service";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  profileLoginData: ProfileData;

  constructor(private profileService: ProfileService) {
    this.profileLoginData = {
      firstName: "",
      lastName: "",
      email: "",
      photo: "",
      bio: ""
    };
  }

  loadProfileData() {
    this.profileService.getProfileByUsername(localStorage.getItem("username"))
      .subscribe(
        data => {
          console.log("Everything ok.");
          console.log(data);
          this.profileLoginData.firstName = data["firstName"];
          this.profileLoginData.lastName = data["lastName"];
          this.profileLoginData.email = data["email"];
          this.profileLoginData.bio = data["bio"];
        }, error => {
          console.log("Error happened.");
          console.log(error);
        }
      )
  }

  ngOnInit() {
    this.loadProfileData();
  }

  editProfileDialog(): void {
  };


}
