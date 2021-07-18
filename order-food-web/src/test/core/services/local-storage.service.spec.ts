import { TestBed, inject } from '@angular/core/testing';

import { LocalStorageService } from 'app/core/services/local-storage.service';

describe('LocalStorageService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LocalStorageService]
    });
  });

  it('should be created', inject([LocalStorageService], (service: LocalStorageService) => {
    expect(service).toBeTruthy();
  }));

  it('should correct url', inject([LocalStorageService], (service: LocalStorageService) => {
    expect(service.get(service.baseUrl)).toBe('http://121.196.145.103:32102');
    expect(service.get(service.baseImgUrl)).toBe('http://172.18.8.10/');
    expect(service.get(service.baseImgUploadUrl)).toBe('http://172.18.8.10:8666/api/v1/upload');
  }));
});
