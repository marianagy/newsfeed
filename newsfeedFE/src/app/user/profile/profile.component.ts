import {Component, OnInit, ViewChild} from '@angular/core';
import {ProfileService} from "../profile.service";
import {MatDialog} from "@angular/material";
import {EditProfileComponent, ProfileDialogData} from "../edit-profile/edit-profile.component";
import {EditBioComponent} from "../edit-bio/edit-bio.component";
import {EditPhotoComponent} from "../edit-photo/edit-photo.component";
import {AddArticleComponent} from "../../article-management/add-article/add-article.component";
import {ArticleService} from "../../article-management/article.service";
import {ArticleListComponent} from "../../article-management/article-list/article-list.component";
import {TranslateService} from "@ngx-translate/core";

export interface ProfileData {
  id: any;
  firstName: string;
  lastName: string;
  email: string;
  photo: any;
  bio: string;

}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {


  profileData: ProfileData;
  private new_profile: ProfileDialogData;


  // child component
  @ViewChild(ArticleListComponent) child: ArticleListComponent;

  constructor(private profileService: ProfileService,
              public dialog: MatDialog,
              private articleService: ArticleService,
              private translate: TranslateService
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

  username;

  ngOnInit() {
    this.username = localStorage.getItem("username");
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
          if (data["photo"] == "" || data["photo"] == undefined) {
            this.profileData.photo = "https://images.vexels.com/media/users/3/137047/isolated/preview/5831a17a290077c646a48c4db78a81bb-user-profile-blue-icon-by-vexels.png";
          } else {
            this.profileData.photo = data["photo"];
          }


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
      if (result != undefined) {
        console.log('The dialog was closed');
        console.log(result);
        this.new_profile = result;
        this.profileData.firstName = this.new_profile.firstName;
        this.profileData.lastName = this.new_profile.lastName;
        this.profileData.email = this.new_profile.email;


      }
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
      if (result != undefined) {
        console.log('The dialog was closed');
        console.log(result);
        this.new_profile = result;
        this.profileData.bio = this.new_profile.bio;
      }
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
      if (result != undefined) {
        console.log('The dialog was closed');
        console.log(result);
        this.new_profile = result;
        if (this.new_profile.photo != undefined) {
          this.profileData.photo = this.new_profile.photo;
        }

        // @ts-ignore
        this.profileService.updateProfileInfo(this.new_profile).subscribe(res => this.profile = res);
      }
    });
  }

  addArticleDialog() {
    const dialogRef = this.dialog.open(AddArticleComponent, {
      width: '1200px',
      height: '90vh',
      data: {"user": localStorage}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result);

      // call child component method
      this.child.loadArticles();
    });

  }


}
