import { Component, OnInit,Input,SimpleChanges,Output} from '@angular/core';
import { ColDef } from 'ag-grid-community';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.css']
})
export class GridComponent implements OnInit {


  @Input() gridColumns : ColDef[];

  @Input() gridRows : any[]

  @Input() gridWidth: number;

  constructor() { }

  ngOnInit() {
  }


}
