import { Injectable } from '@angular/core';
//import { Octokit } from "@octokit/core";
import { from, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GithubService {
  private octokit: any;

  constructor() {
    const token = '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';
    // this.octokit = new Octokit({
    //   auth: token
    // });
  }

  getRepoInfo(username: string, repoName: string, token: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${token}`
      }
    }));
  }

  getRepoCommits(username: string, repoName: string, token: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/commits", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${token}`
      }
    }));
  }

  getRepoIssues(username: string, repoName: string, token: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/issues", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${token}`
      }
    }));
  }

  getRepoBranches(username: string, repoName: string, token: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/branches", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${token}`
      }
    }));
  }

  getRepoTags(username: string, repoName: string, token: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/tags", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${token}`
      }
    }));
  }

  getRepoPulls(username: string, repoName: string, token: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/pulls", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${token}`
      }
    }));
  }
}