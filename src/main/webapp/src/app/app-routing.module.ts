import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoursesComponent } from './pages/courses/courses.component';
import { StudentsComponent } from './pages/students/students.component';
import { RepositoryInfoComponent } from './pages/repository-info/repository-info.component';
import { GroupsComponent } from './pages/groups/groups.component';
import { RepositoriesComponent } from './pages/repositories/repositories.component';

const routes: Routes = [
  {path: 'cursos', component: CoursesComponent},
  {path: 'estudiantes', component: StudentsComponent},
  {path: 'repositorio', component: RepositoryInfoComponent},
  {path: 'grupos', component: GroupsComponent},
  {path: 'repositorios', component: RepositoriesComponent},
  {path: '', redirectTo: '/cursos', pathMatch: 'full'}
  //{path: '**', component: PageNotFoundComponent} TODO
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
