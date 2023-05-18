import { Component } from '@angular/core';
import { HttpService } from '../../services/http.service/http.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.scss']
})
export class StudentsComponent {
  courseId: number | undefined;
  students: any[] = [];

  constructor(private activatedRoute: ActivatedRoute, private httpService: HttpService, private router: Router) {
    this.activatedRoute.queryParams.subscribe(params => {
      this.courseId = params['cursoId'];

      if(this.courseId == undefined)
        this.router.navigate(['/cursos']);
      else
        this.httpService.get('/courses/students?id=' + this.courseId)
        .subscribe(
          (response: any) => this.students = response.data
        );
    });
  }
}
