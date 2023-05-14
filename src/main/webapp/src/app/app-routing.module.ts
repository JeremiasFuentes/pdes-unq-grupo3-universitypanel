import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoursesComponent } from './pages/courses/courses.component';
import { GroupsComponent } from './pages/groups/groups.component';

const routes: Routes = [
  {path: 'cursos', component: CoursesComponent},
  {path: 'grupos', component: GroupsComponent},
  {path: '', redirectTo: '/cursos', pathMatch: 'full'}
  //{path: '**', component: PageNotFoundComponent} TODO
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
