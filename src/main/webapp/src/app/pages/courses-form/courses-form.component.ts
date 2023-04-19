import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpService } from 'src/app/services/http.service/http.service';

@Component({
  selector: 'app-courses-form',
  templateUrl: './courses-form.component.html',
  styleUrls: ['./courses-form.component.scss']
})
export class CoursesFormComponent {
  courseForm: FormGroup;
  subjects: any[] = [];

  showErrors: boolean = false;

  constructor(private formBuilder: FormBuilder, private httpService: HttpService) {
    this.courseForm = formBuilder.group({
      name: ['', Validators.required],
      year: ['', Validators.required],
      semester: ['', Validators.required],
      subject: ['', Validators.required]
    });
  }
  
  ngOnInit() {
    this.httpService.get('/subjects/')
    .subscribe(response => {
      this.subjects = response.data;
    });
  }

  saveCourse() {
    if(this.courseForm.invalid) {
      this.showErrors = true;
      return;
    }
    
    const body = {
      name : this.courseForm.controls['name'].value,
      year : this.courseForm.controls['year'].value,
      semester : this.courseForm.controls['semester'].value,
      subject : {
        id: this.courseForm.controls['subject'].value
      }
    };

    this.httpService.post('/courses/', body)
    .subscribe(_ => location.reload());
  }
}
