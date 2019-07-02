import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavigationModule} from "./navigation/navigation.module";
import {MatButtonModule, MatCheckboxModule} from "@angular/material";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginguardGuard} from "./user/loginguard.guard";
import {TokenInterceptorService} from "./token-interceptor.service";
import {HTTP_INTERCEPTORS, HttpClient} from "@angular/common/http";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";

export function HttpLoaderFactory(http: HttpClient) {

  return new TranslateHttpLoader(http, './assets/i18n/', '.json');

}

@NgModule({
  declarations: [
    AppComponent,


  ],
  imports: [
    TranslateModule.forRoot({

      loader: {

        provide: TranslateLoader,

        useFactory: HttpLoaderFactory,

        deps: [HttpClient]

      }

    }),
    BrowserModule,
    AppRoutingModule,
    NavigationModule,
    MatButtonModule,
    MatCheckboxModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    // InternationalisationModule

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
