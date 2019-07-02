import {TestBed} from '@angular/core/testing';

import {InternationalisationService} from './internationalisation.service';

describe('InternationalisationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InternationalisationService = TestBed.get(InternationalisationService);
    expect(service).toBeTruthy();
  });
});
