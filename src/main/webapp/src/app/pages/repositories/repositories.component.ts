import { Component, ViewChild } from "@angular/core";
import { HttpService } from "../../services/http.service/http.service";
import { ActivatedRoute, Router } from "@angular/router";
import { RepositoriesFormComponent } from "../repositories-form/repositories-form.component";

@Component({
  selector: "app-repositories",
  templateUrl: "./repositories.component.html",
  styleUrls: ["./repositories.component.scss"],
})
export class RepositoriesComponent {
  @ViewChild('confirmDeleteModalClosebutton') confirmDeleteModalClosebutton: any;
  @ViewChild("repositoriesForm", { static: false })
  
  repositoriesForm!: RepositoriesFormComponent;
  courseId: number | undefined;
  groupId: number | undefined;
  repoIdSelected: number | undefined;

  repositories: any[] = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private httpService: HttpService,
    private router: Router
  ) {
    this.activatedRoute.queryParams.subscribe((params) => {
      this.courseId = params["cursoId"];
      this.groupId = params["grupoId"];
      if (this.courseId == undefined || this.groupId == undefined)
        this.router.navigate(["/cursos"]);
      else this.loadRepos();
    });
  }

  loadRepos() {
    this.httpService
      .get("/groups/getAllRepositories?groupId=" + this.groupId)
      .subscribe((response: any) => (this.repositories = response.data));
  }

  deleteRepo() {
    this.httpService
      .delete("/repositories/" + this.courseId + "/delete/" + this.repoIdSelected)
      .subscribe((_: any) => {
        this.loadRepos()
        this.confirmDeleteModalClosebutton.nativeElement.click();
      });
  }

  setRepoIdSelected(repoId: number) {
    this.repoIdSelected = repoId;
    if (this.repositoriesForm) {
      this.repositoriesForm.repoId = this.repoIdSelected;
    }
  }
}
