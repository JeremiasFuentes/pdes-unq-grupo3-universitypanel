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
import { GroupsComponent } from './pages/groups/groups.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CoursesComponent,
    CoursesFormComponent,
    GroupsComponent,
    StudentsFormComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
