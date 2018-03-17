import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  createEmitter(): void {
    debugger
    const eventSource: EventSource = new EventSource('http://localhost:8080/emitter');
    console.log(eventSource)
  }
}
