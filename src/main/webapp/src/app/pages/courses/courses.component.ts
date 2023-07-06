import { Component, ViewChild } from '@angular/core';
import { HttpService } from '../../services/http.service/http.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent {
  @ViewChild('confirmDeleteModalCloseButton') confirmDeleteModalCloseButton: any;

  courses: any[] = [];

  selectedCourse: any;

  constructor(private httpService: HttpService, private toastr: ToastrService) {
    this.loadCourses();
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

  deleteCourseModal(event: any, courseId: number) {
    this.selectedCourse = courseId;
    event.stopPropagation();
  }

  deleteCourse() {
    this.httpService.delete('/courses/' + this.selectedCourse)
    .subscribe(
      (_: any) => {
        this.toastr.success('Se eliminó el curso satisfactoriamente', 'Éxito');

        this.loadCourses();
        this.confirmDeleteModalCloseButton.nativeElement.click();
      }
    );
  }
}
