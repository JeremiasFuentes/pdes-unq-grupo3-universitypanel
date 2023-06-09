import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { enviroment } from 'src/enviroments/enviroment.dev';

@Injectable({
  providedIn: 'root',
})
export class HttpService { 
  private baseURL: string = enviroment.baseURL;
 
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

  login(creds: any){
    console.log(creds);
    return this.http.post(this.baseURL + "/login", creds, {
      observe: 'response'
    }).pipe(map((response: HttpResponse<any>) => {
      const body = response.body;
      const headers = response.headers;
      
      const bearerToken = headers.get('Authorization')!;
      const token = bearerToken?.replace('Bearer ', "");
      localStorage.setItem('token', token);
      

      return body;
    }))
  }

  getToken(){
    return localStorage.getItem('token');
  }
}
