import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SharedPopupModalComponent } from './shared-popup-modal.component';

describe('SharedPopupModalComponent', () => {
  let component: SharedPopupModalComponent;
  let fixture: ComponentFixture<SharedPopupModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SharedPopupModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SharedPopupModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
