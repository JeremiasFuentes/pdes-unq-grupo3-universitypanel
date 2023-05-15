import { Component } from '@angular/core';
import { HttpService } from '../../services/http.service/http.service';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';

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

  setGroupIdSelected(groupId: number){
    this.selectedGroup = groupId;
  }

  addStudent() {
    if(this.selectedStudent == null)
      return;

    this.httpService.put('/groups/' + this.selectedGroup + '/addStudent?studentDni=' + this.selectedStudent, null)
        .subscribe(
          (_: any) => this.loadGroupStudents(this.selectedGroup)
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

  deleteGroup(){
    this.httpService.delete('/groups/' + this.selectedGroup + '/delete').subscribe(
    (response: any) => this.loadGroups()
    )
  }

  
  closeModal() {
    const modal = document.getElementById('confirmDeleteModal');
    if (modal) {
      modal.classList.remove('show');
      modal.style.display = 'none';
      const modalBackdrop = document.getElementsByClassName('modal-backdrop')[0];
      if (modalBackdrop) {
        modalBackdrop.parentNode?.removeChild(modalBackdrop);
      }
    }
  }
}
