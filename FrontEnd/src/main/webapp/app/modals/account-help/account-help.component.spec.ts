import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountHelpComponent } from './account-help.component';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorizationService } from '../../services/authorizationService.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AccountHelpComponent', () => {
  let component: AccountHelpComponent;
  let fixture: ComponentFixture<AccountHelpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule,HttpClientTestingModule],
      declarations: [ AccountHelpComponent ],
      providers: [NgbActiveModal,AuthorizationService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountHelpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
