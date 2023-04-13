import { Component } from '@angular/core';
import { HttpService } from '../../services/http.service/http.service';

@Component({
  selector: 'app-courses-form',
  templateUrl: './courses-form.component.html',
  styleUrls: ['./courses-form.component.scss']
})
export class CoursesFormComponent {
  courses: any[] = [];

  constructor(private httpService: HttpService) {
    this.httpService.get('/courses/')
    .subscribe(
      (response: any) => this.courses = response.data
    );
  }
}
