import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class HttpService { 
  private baseURL: string = "http://localhost:8080/";
 
  constructor(private http: HttpClient) {}
 
  get(): Observable<any> {
    return this.http.get(this.baseURL + 'student/helloworld')
  }
}
