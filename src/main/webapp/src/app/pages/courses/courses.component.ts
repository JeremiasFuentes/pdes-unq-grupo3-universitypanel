import { Component, ViewChild } from '@angular/core';
import { HttpService } from '../../services/http.service/http.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent {
  @ViewChild('confirmDeleteModalCloseButton') confirmDeleteModalCloseButton: any;

  courses: any[] = [];

  selectedCourse: any;

  constructor(private httpService: HttpService) {
    this.loadCourses();
  }

  ngOnInit() {
    $('table').on('click', 'button', function(event) {
      console.log("lleguie");
      event.stopPropagation();
    });
  }

  loadCourses() {
    this.httpService.get('/courses/')
    .subscribe(
      (response: any) => this.courses = response.data
    );
  }

  setCourseIdSelected(courseId: number){
    this.selectedCourse = courseId;
  }

  deleteCourse() {
    this.httpService.delete('/courses/' + this.selectedCourse)
    .subscribe(
      (_: any) => {
        this.loadCourses();
        this.confirmDeleteModalCloseButton.nativeElement.click();
      }
    );
  }
}
