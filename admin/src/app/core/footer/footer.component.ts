import { Component, OnInit } from '@angular/core';
import { DateTimeProviderService } from '../../shared/services/date-time-provider.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  yearCreated: number = 2018;
  currentYear: number;

  constructor(private dateTimeProviderService: DateTimeProviderService) { }

  ngOnInit() {
    this.currentYear = this.dateTimeProviderService.currentYear();
  }
}
