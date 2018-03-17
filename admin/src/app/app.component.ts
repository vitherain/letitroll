import {Component, OnInit} from '@angular/core';
import * as Rx from 'rxjs/Rx';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  eventSource: any;

  EventSource: any = window['EventSource'];

  private onMessageSubj: Rx.Subject<String> = new Rx.Subject();

  onMessage$: Rx.Observable<String> = this.onMessageSubj.asObservable();

  ngOnInit(): void {
    //this.onMessage$.subscribe(console.log);
  }

  createEmitter(): void {
    this.eventSource = new this.EventSource('http://localhost:8080/emitter');
    this.eventSource.onmessage = (msg) => {
      const data: String = JSON.parse(msg.data).message;
      console.log('came message', data);
      this.onMessageSubj.next(data);
    };
  }
}
