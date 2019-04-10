import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavbarComponent} from './navbar/navbar.component';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "../user/login/login.component";
import {MatBadgeModule, MatButtonModule, MatIconModule, MatMenuModule, MatToolbarModule} from "@angular/material";
import {UserModule} from "../user/user.module";
import {RegisterComponent} from "../user/register/register.component";
import {ProfileComponent} from "../user/profile/profile.component";
import {LoginguardGuard} from "../user/loginguard.guard";
import {HomePageManagementModule} from "../home-page-management/home-page-management.module";
import {HomePageComponent} from "../home-page-management/home-page/home-page.component";
import {ArticleComponent} from "../article-management/article/article.component";
import {ArticleManagementModule} from "../article-management/article-management.module";


const loginRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent, canActivate: [LoginguardGuard]},
  {path: 'home-page', component: HomePageComponent, canActivate: [LoginguardGuard]},
  {path: '', pathMatch: 'full', redirectTo: '/login'},
  {path: 'article/:id', component: ArticleComponent, canActivate: [LoginguardGuard]}
];

@NgModule({
  declarations: [NavbarComponent],
  exports: [
    NavbarComponent,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    CommonModule


  ],
  imports: [
    CommonModule,
    RouterModule.forChild(loginRoutes),
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    MatBadgeModule,
    MatIconModule,
    UserModule,
    HomePageManagementModule,
    ArticleManagementModule
  ]
})
export class NavigationModule {
}
