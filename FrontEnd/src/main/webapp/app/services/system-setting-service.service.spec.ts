import { TestBed } from '@angular/core/testing';

import { SystemSettingServiceService } from './system-setting-service.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('SystemSettingServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule]
  }));

  it('should be created', () => {
    const service: SystemSettingServiceService = TestBed.get(SystemSettingServiceService);
    expect(service).toBeTruthy();
  });
});
