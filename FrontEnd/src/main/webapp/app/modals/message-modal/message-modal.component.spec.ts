import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageModalComponent } from './message-modal.component';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { RouterTestingModule } from '@angular/router/testing';

describe('MessageModalComponent', () => {
  let component: MessageModalComponent;
  let fixture: ComponentFixture<MessageModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessageModalComponent ],
      providers: [NgbActiveModal],
      imports: [RouterTestingModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessageModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  
});
