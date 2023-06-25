import { Component, ViewChild } from '@angular/core';
import { HttpService } from '../../services/http.service/http.service';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.scss']
})
export class GroupsComponent {
  @ViewChild('confirmDeleteModalCloseButton') confirmDeleteModalCloseButton: any;
  courseId: number | undefined;

  students: any[] = [];
  groups: any[] = [];
  groupStudents: any[] = [];
  groupNotes: any[] = [];

  selectedGroup: any;
  selectedStudent: any = null;
  selectedNote: string = '';

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

  loadGroupNotes(groupId: number) {
    this.selectedGroup = groupId;

    this.groupNotes = [];
    this.httpService.get('/groups/' + groupId + '/notes')
    .subscribe(
      (response: any) => this.groupNotes = response
    );
  }

  setGroupIdSelected(groupId: number){
    this.selectedGroup = groupId;
  }

  addStudent() {
    if(this.selectedStudent == null)
      return;

    this.httpService.put('/groups/' + this.selectedGroup + '/addStudent?studentDni=' + this.selectedStudent, null)
    .subscribe(
      (_: any) => {
        this.loadGroupStudents(this.selectedGroup);
        this.loadGroups();
      }
    );
  }

  addNote() {
    if(this.selectedNote == null)
      return;

    this.httpService.post('/groups/' + this.selectedGroup + '/notes', this.selectedNote)
    .subscribe(
      (response: any) => {
        this.loadGroupNotes(this.selectedGroup);
      }
    );
  }

  goToRepositories(grupoId: number){
    // Configurar los parámetros de navegación
    const navigationExtras: NavigationExtras = {
      queryParams: { grupoId: grupoId.toString(), cursoId: this.courseId?.toString()}
    };

    // Redireccionar a la página de repositorios del grupo
    this.router.navigate(['/repositorios'], navigationExtras);
  }

  deleteGroup() {
    this.httpService.delete('/groups/' + this.selectedGroup + '/delete')
    .subscribe(
      (_: any) => {
        this.loadGroups();
        this.confirmDeleteModalCloseButton.nativeElement.click();
      }
    )
  }

  deleteNote(indexNote: number) {
    this.httpService.delete('/groups/' + this.selectedGroup + '/notes/' + indexNote)
    .subscribe(
      (_: any) => {
        this.loadGroupNotes(this.selectedGroup);
      }
    )
  }
}
