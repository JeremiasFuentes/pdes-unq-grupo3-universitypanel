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

  getRepoInfo(username: string, repoName: string, tokenp: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${tokenp}`
      }
    }));
  }

  getRepoCommits(username: string, repoName: string, tokenp: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/commits", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${tokenp}`
      }
    }));
  }

  getRepoIssues(username: string, repoName: string, tokenp: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/issues", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${tokenp}`
      }
    }));
  }

  getRepoBranches(username: string, repoName: string, tokenp: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/branches", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${tokenp}`
      }
    }));
  }

  getRepoTags(username: string, repoName: string, tokenp: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/tags", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${tokenp}`
      }
    }));
  }

  getRepoPulls(username: string, repoName: string, tokenp: string): Observable<any> {
    return from(this.octokit.request("GET /repos/{owner}/{repo}/pulls", {
      owner: username,
      repo: repoName,
      headers: {
        authorization: `Bearer ${tokenp}`
      }
    }));
  }
}