import {Component, Input, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

export class Language {
  Id: string;
  Name: string;

  constructor(lngId: string, lngName: string) {

    this.Id = lngId;
    this.Name = lngName;
  }
}

@Component({
  selector: 'app-translate-content',
  templateUrl: './translate-content.component.html',
  styleUrls: ['./translate-content.component.scss']
})

export class TranslateContentComponent implements OnInit {

  @Input()
  allLanguages: Language[];

  constructor(private translate: TranslateService) {
  }

  ngOnInit(): void {

    this.allLanguages = [
      new Language('en', 'En'),
      new Language('ro', 'Ro')
    ];

  }

  public changeLanguage(event, id) {
    this.translate.use(id);

  }

}

