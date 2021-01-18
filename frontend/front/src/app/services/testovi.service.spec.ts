import { TestBed } from '@angular/core/testing';

import { TestoviService } from './testovi.service';

describe('TestoviService', () => {
  let service: TestoviService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TestoviService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
