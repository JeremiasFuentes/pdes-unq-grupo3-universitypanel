import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { HttpService } from "src/app/services/http.service/http.service";

@Component({
  selector: "app-students-form",
  templateUrl: "./students-form.component.html",
  styleUrls: ["./students-form.component.scss"],
})
export class StudentsFormComponent {
  studentForm: FormGroup;
  groups: any[] = [];

  showErrors: boolean = false;

  constructor(formBuilder: FormBuilder, private httpService: HttpService) {
    this.studentForm = formBuilder.group({
      dni: ["", Validators.required],
      name: ["", Validators.required],
      mail: ["", Validators.required],
      group: ["", Validators.required],
    });
  }

  // ngOnInit() {
  //   this.httpService.get('/groups/')
  //   .subscribe(response => {
  //     this.groups = response.data;
  //   });
  // }

  saveStudent() {
    if (this.studentForm.invalid) {
      this.showErrors = true;
      return;
    }

    const body = {
      dni: this.studentForm.controls["dni"].value,
      name: this.studentForm.controls["name"].value,
      mail: this.studentForm.controls["mail"].value,
      group: this.studentForm.controls["group"].value,
    };

    this.httpService
      .post("/students/", body)
      .subscribe((_) => location.reload());
  }
}
