import { HostListener, Component,ChangeDetectorRef  } from '@angular/core';
import {SystemSettingServiceService} from '../services/system-setting-service.service'


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  show = false;
  constructor(private systemSetter: SystemSettingServiceService){
    systemSetter.changeEmitted$.subscribe(result =>{
      this.show = result;
    })

  }
  
}
