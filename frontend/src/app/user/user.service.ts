import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  login (username: string, password : string) {
    const body = {username: username, password: password};
    this.http.post('http://localhost:8080/api/auth/login', body).subscribe(config => {
        console.log('Updated config:', config);
      }
    );
  }

  register (username: string, password : string) {
    const body = {username: username, password: password};
    this.http.post('http://localhost:8080/api/auth/register', body).subscribe(config => {
        console.log('Updated config:', config);
      }
    );
  }
}
