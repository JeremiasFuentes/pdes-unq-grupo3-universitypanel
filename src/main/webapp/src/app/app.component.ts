import { Component } from '@angular/core';
import { HttpService } from './services/http.service/http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  helloWorld: string = '';

  constructor(private httpService: HttpService) {
    this.httpService.get()
    .subscribe(
      (response) => {
        console.log(response)
        this.helloWorld = response.data; 
      }
    );
  }
}
