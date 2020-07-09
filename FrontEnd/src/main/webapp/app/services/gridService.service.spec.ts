import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { GridService } from './gridService.service';

describe('gridService', () => {
  let service: GridService;
  let mockRouter = {
    navigate: jasmine.createSpy('navigate')
  } 

  beforeEach(() => {
  TestBed.configureTestingModule({
    imports:  [HttpClientTestingModule],
    providers: [GridService], 
  });
  service = TestBed.get(GridService);
  });

  it('should be created', () => {
    const service: GridService = TestBed.get(GridService);
    expect(service).toBeTruthy();
  });

  it('should setup grid formats based on CellRenderer and field name', () =>{
    //Mocked GridMetadata. 
    let mockedGridMeta = [
      {headerName: "Default", field: "field 1", cellRenderer: "DEFAULT"},
      {headerName: "DOLLAR", field: "field 2 ", cellRenderer: "DOLLAR"},
      {headerName: "Units", field: "field 3", cellRenderer: "UNITS"},
      {headerName: "HyperLinkField", field: "accountnumber", cellRenderer: "DEFAULT"}
    ]
  
    let formattedGridMeta = service.formatGridData(mockedGridMeta);

    expect(formattedGridMeta[0].cellRenderer == null).toBeTruthy();
    //CellRenderer is a function, so we just check that the function name is correct. 
    expect(formattedGridMeta[1].cellRenderer.name =="currencyFormatter").toBeTruthy();
    expect(formattedGridMeta[2].cellRenderer.name =="unitsFormatter").toBeTruthy();

    /*Account number is special because we need a hyperlink, so we check that 
      the CellRendererFramework is seup as well as parameters for the renderer. 
      se just make sure the cellRendererParams are setup, the params are actually just the 
      element itself. 
    */
    let linkField = formattedGridMeta[3];
    expect(linkField.cellRenderer == null && linkField.cellRendererFramework.name =='GridRenderer'
    && linkField.cellRendererParams['inRouterLink'] !== undefined).toBeTruthy();


  })



});
