import {Injectable} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

@Injectable({
  providedIn: 'root'
})
export class InternationalisationService {

  constructor(private translate: TranslateService) {
  }
}
