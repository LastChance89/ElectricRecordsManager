import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientDashboardComponent } from './client-dashboard.component';
import { ClientDisplay } from './client-display/client-display.component';
import { ClientSearch } from './client-search/client-search.component';
import { FormsModule } from '@angular/forms';
import { GridComponent } from '../grid/grid.component';
import {AgGridModule} from 'ag-grid-angular';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { GridService } from '../services/gridService.service';
import { ClientService } from '../services/client-service.service';
describe('ClientDashboardComponent', () => {
  let component: ClientDashboardComponent;
  let fixture: ComponentFixture<ClientDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule,AgGridModule,HttpClientTestingModule],
      declarations: [ ClientDashboardComponent,ClientSearch, ClientDisplay,GridComponent, ClientSearch],
      providers: [GridService,ClientService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
