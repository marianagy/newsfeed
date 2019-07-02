import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TranslateContentComponent} from './translate-content.component';

describe('TranslateContentComponent', () => {
  let component: TranslateContentComponent;
  let fixture: ComponentFixture<TranslateContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TranslateContentComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TranslateContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
