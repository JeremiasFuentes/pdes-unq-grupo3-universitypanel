import { Injectable } from '@angular/core';
import { Octokit } from "@octokit/core";
import { from, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GithubService {
  private octokit: Octokit;

  constructor() {
    const token = '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';
    this.octokit = new Octokit({
      auth: token
    });
  }

  getRepoInfo(username: string, repoName: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}", {
      owner: username,
      repo: repoName,
    }));
  }

  getRepoCommits(username: string, repoName: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/commits?sha=main", {
      owner: username,
      repo: repoName,
    }));
  }

  getRepoIssues(username: string, repoName: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/issues?sha=main", {
      owner: username,
      repo: repoName,
    }));
  }

  getRepoBranches(username: string, repoName: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/branches", {
      owner: username,
      repo: repoName,
    }));
  }

  getRepoTags(username: string, repoName: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/tags?sha=main", {
      owner: username,
      repo: repoName,
    }));
  }

  getRepoPulls(username: string, repoName: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/pulls?sha=main", {
      owner: username,
      repo: repoName,
    }));
  }
}