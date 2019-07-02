import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateContentComponent} from './translate-content/translate-content.component';
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {HttpLoaderFactory} from "../app.module";
import {HttpClient} from "@angular/common/http";
import {MatButtonModule, MatMenuModule} from "@angular/material";
import {InternationalisationService} from "./internationalisation.service";

@NgModule({
  declarations: [TranslateContentComponent],
  imports: [
    TranslateModule.forChild({

      loader: {

        provide: TranslateLoader,

        useFactory: HttpLoaderFactory,

        deps: [HttpClient]

      }

    }),
    CommonModule,
    MatMenuModule,
    MatButtonModule,
    TranslateModule
  ],
  exports: [TranslateContentComponent, TranslateModule, MatMenuModule],
  providers: [InternationalisationService]
})
export class InternationalisationModule {
}
