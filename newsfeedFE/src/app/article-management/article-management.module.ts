import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ArticleComponent} from './article/article.component';
import {ArticleListComponent} from './article-list/article-list.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
  MatButtonModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatSelectModule,
  MatSnackBarModule
} from "@angular/material";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";
import {AppRoutingModule} from "../app-routing.module";
import {TokenInterceptorService} from "../token-interceptor.service";
import {AddArticleComponent} from './add-article/add-article.component';
import {EditArticleComponent} from './edit-article/edit-article.component';
import {AddCommentComponent} from './add-comment/add-comment.component';
import {EditCommentComponent} from './edit-comment/edit-comment.component';
import {ClickOutsideModule} from "ng-click-outside";


@NgModule({
  declarations: [ArticleComponent, ArticleListComponent, AddArticleComponent, EditArticleComponent, AddCommentComponent, EditCommentComponent],
  imports: [

    CommonModule,
    BrowserAnimationsModule,
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
    AppRoutingModule,
    ClickOutsideModule,
    MatCheckboxModule,
    MatPaginatorModule


  ],
  exports: [
    ArticleListComponent,
    ArticleComponent,
    AddArticleComponent,
    EditArticleComponent,
    AddCommentComponent,
    EditCommentComponent,
    RouterModule

  ],
  providers: [TokenInterceptorService]
})
export class ArticleManagementModule {
}
