import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoursesComponent } from './pages/courses/courses.component';
import { StudentsComponent } from './pages/students/students.component';

const routes: Routes = [
  {path: 'cursos', component: CoursesComponent},
  {path: 'estudiantes', component: StudentsComponent},
  {path: '', redirectTo: '/cursos', pathMatch: 'full'}
  //{path: '**', component: PageNotFoundComponent} TODO
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
