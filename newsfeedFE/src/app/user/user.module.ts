import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './login/login.component';
import {HttpClientModule} from "@angular/common/http";
import {
  MatButtonModule,
  MatCardModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatNativeDateModule,
  MatSelectModule,
  MatSnackBarModule
} from "@angular/material";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {RegisterComponent} from './register/register.component';
import {BrowserModule} from "@angular/platform-browser";
import {ProfileComponent} from './profile/profile.component';
import {EditProfileComponent} from './edit-profile/edit-profile.component';
import {EditBioComponent} from './edit-bio/edit-bio.component';
import {EditPhotoComponent} from './edit-photo/edit-photo.component';
import {ArticleManagementModule} from "../article-management/article-management.module";
import {AddArticleComponent} from "../article-management/add-article/add-article.component";
import {EditArticleComponent} from "../article-management/edit-article/edit-article.component";
import {EditCommentComponent} from "../article-management/edit-comment/edit-comment.component";
import {AddCommentComponent} from "../article-management/add-comment/add-comment.component";


@NgModule({
  declarations: [LoginComponent, RegisterComponent, ProfileComponent, EditProfileComponent, EditBioComponent, EditPhotoComponent],
  imports: [
    BrowserAnimationsModule,
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    HttpClientModule,
    MatIconModule,
    FormsModule,
    MatCardModule,
    MatListModule,
    MatChipsModule,
    MatExpansionModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatDialogModule,
    MatSelectModule,
    ReactiveFormsModule,
    BrowserModule,
    ArticleManagementModule,
  ],
  exports: [LoginComponent, RouterModule],
  entryComponents: [EditProfileComponent,
    EditBioComponent,
    EditPhotoComponent,
    AddArticleComponent,
    EditArticleComponent,
    EditCommentComponent,
    AddCommentComponent],


})

export class UserModule {
}
