import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavbarComponent} from './navbar/navbar.component';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "../user/login/login.component";
import {MatBadgeModule, MatButtonModule, MatIconModule, MatMenuModule, MatToolbarModule} from "@angular/material";
import {UserModule} from "../user/user.module";
import {RegisterComponent} from "../user/register/register.component";
import {ProfileComponent} from "../user/profile/profile.component";

const loginRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent}
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
    UserModule
  ]
})
export class NavigationModule {
}
