import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { HttpService } from "src/app/services/http.service/http.service";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: "app-students-form",
  templateUrl: "./students-form.component.html",
  styleUrls: ["./students-form.component.scss"],
})
export class StudentsFormComponent {
  public studentForm: FormGroup;
  groups: any[] = [];

  showErrors: boolean = false;

  constructor(formBuilder: FormBuilder, private httpService: HttpService, private toastr: ToastrService) {
    this.studentForm = formBuilder.group({
      dni: ["", [Validators.required, Validators.pattern('[0-9]{8,8}')]],
      name: ["", Validators.required],
      mail: ["", [Validators.required, Validators.pattern('[a-zA-Z\.\-\_]+\@[a-zA-Z]+\.[a-zA-Z]+')]],
    });
  }

  saveStudent() {
    if (this.studentForm.invalid) {
      this.showErrors = true;
      return;
    } else
      this.showErrors = false;

    const body = {
      dni: this.studentForm.controls["dni"].value,
      name: this.studentForm.controls["name"].value,
      mail: this.studentForm.controls["mail"].value,
    };

    this.studentForm.controls["dni"].setValue("");
    this.studentForm.controls["name"].setValue("");
    this.studentForm.controls["mail"].setValue("");

    this.httpService
    .post("/students/", body)
    .subscribe(
      (_) => this.toastr.success('Se creó el estudiante satisfactoriamente', 'Éxito'),
      (error) => this.toastr.error(error.error, 'Error')
    );
  }
}
