import { TestBed, inject } from '@angular/core/testing';

import { LoggerFactory } from 'app/core/services/logger-factory';

describe('LoggerFactoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LoggerFactory]
    });
  });

  it('should be created', inject([LoggerFactory], (service: LoggerFactory) => {
    expect(service).toBeTruthy();
  }));
});
