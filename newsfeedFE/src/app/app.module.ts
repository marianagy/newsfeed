import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavigationModule} from "./navigation/navigation.module";
import {MatButtonModule, MatCheckboxModule} from "@angular/material";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginguardGuard} from "./user/loginguard.guard";


@NgModule({
  declarations: [
    AppComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NavigationModule,
    MatButtonModule,
    MatCheckboxModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [LoginguardGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
