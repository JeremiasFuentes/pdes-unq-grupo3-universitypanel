import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoursesComponent } from './pages/courses/courses.component';
import { StudentsComponent } from './pages/students/students.component';
import { RepositoryInfoComponent } from './pages/repository-info/repository-info.component';
import { GroupsComponent } from './pages/groups/groups.component';
import { RepositoriesComponent } from './pages/repositories/repositories.component';
import { LoginComponent } from './pages/login/login/login.component';
import { AuthGuard } from './helpers/auth.guard';

const routes: Routes = [
  {path: 'cursos', component: CoursesComponent, canActivate: [AuthGuard]},
  {path: 'estudiantes', component: StudentsComponent, canActivate: [AuthGuard]},
  {path: 'repositorio', component: RepositoryInfoComponent, canActivate: [AuthGuard]},
  {path: 'grupos', component: GroupsComponent, canActivate: [AuthGuard]},
  {path: 'repositorios', component: RepositoriesComponent, canActivate: [AuthGuard]},
  {path: 'login' , component: LoginComponent}
  
  //{path: '**', component: PageNotFoundComponent} TODO
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
