import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-route-header',
  templateUrl: './route-header.component.html',
  styleUrls: ['./route-header.component.scss']
})
export class RouteHeaderComponent {
  @Input() header: string;

  constructor() {}
}
