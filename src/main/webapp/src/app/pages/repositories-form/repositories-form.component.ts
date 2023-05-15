import { Component, Input,OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { HttpService } from 'src/app/services/http.service/http.service';

@Component({
  selector: 'app-repositories-form',
  templateUrl: './repositories-form.component.html',
  styleUrls: ['./repositories-form.component.scss']
})
export class RepositoriesFormComponent {
  @Input() repoId:number |undefined;
  repositoryForm: FormGroup;
  groupId: number | undefined;
  selectedRepo: any = {};

  showErrors: boolean = false;

  constructor(formBuilder: FormBuilder, private httpService: HttpService, private route: ActivatedRoute) {
    this.repositoryForm = formBuilder.group({
      name: ['', Validators.required],
      owner: ['', Validators.required],
      token: ['', Validators.required]
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['repoId'] && !changes['repoId'].firstChange) {
      this.loadRepository();
    }
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.groupId = params['grupoId'];
      this.loadRepository(); 
    });
}
    

loadRepository() {
    if (this.repoId) {
      // Realiza la carga del repositorio aquí
      this.httpService.get('/repositories/' + this.repoId).subscribe(
        (response: any) => {
          this.selectedRepo = response;
          this.setValues();
        },
        (error: any) => {
          console.error('Error al obtener los datos del repositorio', error);
        }
      );
    } else {
      // Si no se proporciona un repoId, restablece los valores del formulario
      this.setValues();
    }
  }
    setValues() {
        const nameControl = this.repositoryForm.get('name');
        const ownerControl = this.repositoryForm.get('owner');
        const tokenControl = this.repositoryForm.get('token');
      
        // Verificar si el control del formulario existe antes de establecer los valores
        if (nameControl) {
          nameControl.setValue(this.selectedRepo.name);
        }
        if (ownerControl) {
          ownerControl.setValue(this.selectedRepo.owner);
        }
        if (tokenControl) {
          tokenControl.setValue(this.selectedRepo.token);
        }
      
    }

  saveRepository() {
    if(this.repositoryForm.invalid) {
      this.showErrors = true;
      return;
    }
    
    const body = {
      name : this.repositoryForm.controls['name'].value,
      owner : this.repositoryForm.controls['owner'].value,
      token : this.repositoryForm.controls['token'].value
    };

    if (this.repoId) {
        this.saveModifiedRepository()

    } else {
        this.httpService.post('/repositories/'+ this.groupId +'/createRepository', body)
        .subscribe(_ => location.reload());
    }

  }

  saveModifiedRepository() {
    if (this.repositoryForm.invalid) {
      this.showErrors = true;
      return;
    }
  
    const modifiedRepo = {
      id: this.selectedRepo.id,
      name : this.repositoryForm.controls['name'].value,
      owner : this.repositoryForm.controls['owner'].value,
      token : this.repositoryForm.controls['token'].value
    };
  
    this.httpService.put('/repositories/'+ this.groupId + '/update/' + modifiedRepo.id, modifiedRepo).subscribe(
      (response: any) => {
        console.log('Repositorio modificado exitosamente');
        location.reload();
        // Realizar otras acciones después de la modificación exitosa
      },
      (error: any) => {
        console.error('Error al modificar el repositorio', error);
      }
    );
  }

}


