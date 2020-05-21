import { TestBed } from '@angular/core/testing';

import { SystemSettingServiceService } from './system-setting-service.service';

describe('SystemSettingServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SystemSettingServiceService = TestBed.get(SystemSettingServiceService);
    expect(service).toBeTruthy();
  });
});
