import { TestBed } from '@angular/core/testing';

import { DashBoardService } from './dash-board-service.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('DashBoardService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule]

  }));

  it('should be created', () => {
    const service: DashBoardService = TestBed.get(DashBoardService);
    expect(service).toBeTruthy();
  });
});
