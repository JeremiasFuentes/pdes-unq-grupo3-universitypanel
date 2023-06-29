import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { ToastrModule } from 'ngx-toastr';

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
import { LoginComponent } from './pages/login/login/login.component';
import { AuthInterceptor } from './helpers/auth.interceptor';

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
    RepositoriesFormComponent,
    LoginComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule,
    ToastrModule.forRoot()
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS , useClass:AuthInterceptor, multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
