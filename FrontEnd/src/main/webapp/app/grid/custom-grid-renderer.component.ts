import { Component } from '@angular/core';
import { AgRendererComponent } from 'ag-grid-angular';

@Component({
    template: `<a [routerLink]="url" >{{this.params.value}}</a>`,
    selector: 'grid-renderer'
  })

export class GridRenderer implements AgRendererComponent{
 
    constructor() { }

    params: any;
    url = '/records';
    
    agInit(params: any){
        this.params = params;
        this.url = this.url+'/'+ this.params.value;
    }
    refresh(params: any): boolean {
        return false;
      }
}


