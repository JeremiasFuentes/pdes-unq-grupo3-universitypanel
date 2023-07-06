import { Component } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from 'src/app/services/http.service/http.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup

  showErrors: boolean = false;

  constructor(formBuilder: FormBuilder, private httpService: HttpService,private router: Router){
    this.loginForm = formBuilder.group({
      email: ['' , Validators.required],
      password: ['' , Validators.required]
    })
  }

  login(){

    if(this.loginForm.invalid) {
      this.showErrors = true;
      return;
    }

    const body = {
      email : this.loginForm.controls['email'].value,
      password : this.loginForm.controls['password'].value
    };

    this.httpService.login(body)
      .subscribe(response => {
        console.log(response)
        this.router.navigate(['/cursos']);
      })

  }



  

}
