import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ClientDisplay } from './client-display.component';
import { ModalService } from '../../services/modal-service.service';
import { GridService } from '../../services/gridService.service';
import { ClientService } from '../../services/clientService.service';
import { AgGridModule } from 'ag-grid-angular';
import { GridComponent } from '../../grid/grid.component';

describe('ClientDisplay', () => {
  let component: ClientDisplay;
  let fixture: ComponentFixture<ClientDisplay>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule,HttpClientTestingModule,AgGridModule ],
      declarations: [ ClientDisplay,GridComponent ],
      providers: [ ModalService, ClientService,GridService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDisplay);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
