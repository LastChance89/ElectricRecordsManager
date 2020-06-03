import { TestBed } from '@angular/core/testing';

import { DashBoardServiceService } from './dash-board-service.service';

describe('DashBoardServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DashBoardServiceService = TestBed.get(DashBoardServiceService);
    expect(service).toBeTruthy();
  });
});
