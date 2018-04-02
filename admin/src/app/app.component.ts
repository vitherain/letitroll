import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  eventSource: any;

  EventSource: any = window['EventSource'];

  sample$: Observable<String>;
  onMessage$: Observable<String>;

  ngOnInit(): void {
    // this.onMessage$.subscribe(console.log);
    this.sample$ = new Observable<String>(observer => {
      observer.next('prvni pokus');

      setTimeout(() => observer.next('druhy pokus'), 3000);
    });
  }

  createEmitter(): void {
    this.eventSource = new this.EventSource('http://localhost:8080/emitter');
    this.onMessage$ = new Observable(observer => {
      // observer.next('startuji observable');

      this.eventSource.onmessage = (msg) => {
        const data: String = JSON.parse(msg.data).message;
        console.log('came message', data);
        observer.next(data);
      };
    });
  }
}
