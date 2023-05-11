import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GithubService } from 'src/app/services/http.service/apigit.service';

@Component({
    selector: 'app-repository-info',
    templateUrl: './repository-info.component.html',
    styleUrls: ['./repository-info.component.scss']
  })
  export class RepositoryInfoComponent implements OnInit {
    user: string ='';
    repoName: string ='';
    repoInfo: any = null;
    commits!: any[];
    issues!: any[];
    branches!: any[];
    tags!: any[];
    pulls!: any[];
  
    constructor(
      private route: ActivatedRoute,
      private githubService: GithubService
    ) { }
  
    ngOnInit() {
      this.route.paramMap.subscribe(params => {
        this.user = params.get('user')?? '';
        this.repoName = params.get('repoName')?? '';
        this.getRepositoryInfo();
        this.getRepoCommits();
        this.getRepoIssues();
        this.getRepoBranches();
        this.getRepoTags();
        this.getRepoPulls();
      });
    }
  
    getRepositoryInfo() {
      this.githubService.getRepoInfo(this.user, this.repoName).subscribe(Response => {
        this.repoInfo = Response.data;
        
      });
    }
  
    getRepoCommits() {
      this.githubService.getRepoCommits(this.user, this.repoName).subscribe(Response => {
        this.commits = Response.data;
      });
    }
  
    getRepoIssues() {
      this.githubService.getRepoIssues(this.user, this.repoName).subscribe(Response => {
        this.issues = Response.data;
      });
    }
  
    getRepoBranches() {
      this.githubService.getRepoBranches(this.user, this.repoName).subscribe(Response => {
        this.branches = Response.data;
      });
    }
  
    getRepoTags() {
      this.githubService.getRepoTags(this.user, this.repoName).subscribe(Response => {
        this.tags = Response.data;
      });
    }
  
    getRepoPulls() {
      this.githubService.getRepoPulls(this.user, this.repoName).subscribe(Response => {
        this.pulls = Response.data;
      });
    }
  }