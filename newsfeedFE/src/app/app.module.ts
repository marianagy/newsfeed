import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavigationModule} from "./navigation/navigation.module";
import {MatButtonModule, MatCheckboxModule} from "@angular/material";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginguardGuard} from "./user/loginguard.guard";
import {TokenInterceptorService} from "./token-interceptor.service";
import {HTTP_INTERCEPTORS} from "@angular/common/http";


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
  providers: [LoginguardGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
