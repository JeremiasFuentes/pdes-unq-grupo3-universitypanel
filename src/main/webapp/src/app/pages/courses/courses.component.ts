import { Component } from '@angular/core';
import { HttpService } from '../../services/http.service/http.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent {
  courses: any[] = [];

  constructor(private httpService: HttpService) {
    this.httpService.get('/courses/')
    .subscribe(
      (response: any) => this.courses = response.data
    );
  }
}
