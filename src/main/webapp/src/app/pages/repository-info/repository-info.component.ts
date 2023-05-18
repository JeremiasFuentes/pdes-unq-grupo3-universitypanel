import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { GithubService } from 'src/app/services/http.service/apigit.service';
import { HttpService } from '../../services/http.service/http.service';


@Component({
    selector: 'app-repository-info',
    templateUrl: './repository-info.component.html',
    styleUrls: ['./repository-info.component.scss']
  })
  export class RepositoryInfoComponent implements OnInit {
    user: string ='';
    repoName: string ='';
    id: string ='';
    token: string = '';
    repoInfo: any = null;
    info: any=null;
    commits!: any[];
    issues!: any[];
    branches!: any[];
    tags!: any[];
    pulls!: any[];
  
    constructor(
      private route: ActivatedRoute,
      private githubService: GithubService,
      private router: Router,
      private httpService: HttpService
    ) { }


  
    ngOnInit() {
      this.route.queryParams.subscribe(params => {
        this.user = params['user'];
        this.repoName = params['repoName'];
        this.id = params['id'];
        this.getRepositoryInDB();
        
      });
    }


    getRepositoryInDB() {
      console.log(this.id);
      if(this.id == undefined)
          this.router.navigate(['/cursos']);
        else
          this.httpService.get('/repositories/' + this.id)
          .subscribe(
            (response: any) => {this.info = response,
            console.log(this.info),
            this.getRepositoryInfo(),
            this.getRepoCommits(),
            this.getRepoIssues(),
            this.getRepoBranches(),
            this.getRepoTags(),
            this.getRepoPulls();
          
          }
          );
    }
    
    getRepositoryInfo() {
      this.githubService.getRepoInfo(this.user, this.repoName, this.info.token).subscribe(Response => {
        this.repoInfo = Response.data;
        
      });
    }
  
    getRepoCommits() {
      this.githubService.getRepoCommits(this.user, this.repoName,this.info.token).subscribe(Response => {
        this.commits = Response.data;
      });
    }
  
    getRepoIssues() {
      this.githubService.getRepoIssues(this.user, this.repoName,this.info.token).subscribe(Response => {
        this.issues = Response.data;
      });
    }
  
    getRepoBranches() {
      this.githubService.getRepoBranches(this.user, this.repoName,this.info.token).subscribe(Response => {
        this.branches = Response.data;
      });
    }
  
    getRepoTags() {
      this.githubService.getRepoTags(this.user, this.repoName,this.info.token).subscribe(Response => {
        this.tags = Response.data;
      });
    }
  
    getRepoPulls() {
      this.githubService.getRepoPulls(this.user, this.repoName,this.info.token).subscribe(Response => {
        this.pulls = Response.data;
      });
    }
  }