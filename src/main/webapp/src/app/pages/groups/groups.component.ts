import { Component } from '@angular/core';
import { HttpService } from '../../services/http.service/http.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.scss']
})
export class GroupsComponent {
  courseId: number | undefined;

  students: any[] = [];
  groups: any[] = [];
  groupStudents: any[] = [];

  selectedGroup: any;
  selectedStudent: any = null;

  constructor(private activatedRoute: ActivatedRoute, private httpService: HttpService, private router: Router) {
    this.activatedRoute.queryParams.subscribe(params => {
      this.courseId = params['cursoId'];

      if(this.courseId == undefined)
        this.router.navigate(['/cursos']);
      else
        this.loadGroups();
    });

    this.httpService.get('/students/')
    .subscribe(
      (response: any) => this.students = response.data
    );
  }

  loadGroups() {
    this.httpService.get('/groups/getAll?courseId=' + this.courseId)
    .subscribe(
      (response: any) => this.groups = response.data
    );
  }

  createGroup() {
    this.httpService.post('/groups/create?courseId=' + this.courseId, null)
    .subscribe(
      (_: any) => this.loadGroups()
    );
  }

  loadGroupStudents(groupId: number) {
    this.selectedGroup = groupId;

    this.groupStudents = [];
    this.httpService.get('/groups/getAllStudents?groupId=' + groupId)
        .subscribe(
          (response: any) => this.groupStudents = response.data
        );
  }

  addStudent() {
    if(this.selectedStudent == null)
      return;

    this.httpService.put('/groups/' + this.selectedGroup + '/addStudent?studentDni=' + this.selectedStudent, null)
        .subscribe(
          (_: any) => this.loadGroupStudents(this.selectedGroup)
        );
  }
}
