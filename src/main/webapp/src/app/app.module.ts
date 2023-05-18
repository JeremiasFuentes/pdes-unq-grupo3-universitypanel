import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoursesComponent } from './pages/courses/courses.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CoursesFormComponent } from './pages/courses-form/courses-form.component';
import { StudentsFormComponent } from './pages/students-form/students-form.component';
import { RepositoryInfoComponent } from './pages/repository-info/repository-info.component';
import { GroupsComponent } from './pages/groups/groups.component';
import { StudentsComponent } from './pages/students/students.component';
import { RouterModule } from '@angular/router';
import { RepositoriesComponent } from './pages/repositories/repositories.component';
import { RepositoriesFormComponent } from './pages/repositories-form/repositories-form.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CoursesComponent,
    CoursesFormComponent,
    StudentsComponent,
    StudentsFormComponent,
    RepositoryInfoComponent,
    GroupsComponent,
    RepositoriesComponent,
    RepositoriesFormComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
