import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HttpService { 
  private baseURL: string = "http://localhost:8080";
 
  constructor(private http: HttpClient) {}
 
  get(endpoint: string): Observable<any> {
    return this.http.get(this.baseURL + endpoint)
  }
 
  put(endpoint: string, body: any): Observable<any> {
    return this.http.put(this.baseURL + endpoint, body)
  }
  
  post(endpoint: string, body: any): Observable<any> {
    return this.http.post(this.baseURL + endpoint, body)
  }

  delete(endpoint: string): Observable<any> {
    return this.http.delete(this.baseURL + endpoint)
  }
}
