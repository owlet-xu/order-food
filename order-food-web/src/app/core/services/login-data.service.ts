import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LocalStorageService } from 'app/core/services/local-storage.service';

@Injectable()

export class LoginDataService {

  constructor(private http: HttpClient, private storage: LocalStorageService) { }

  login(phone: string, passWord: string, back: any) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/login', {
      phone: phone,
      passWord: passWord
    }, { headers: header }).subscribe(
      (val) => {
        const data = JSON.parse(JSON.stringify(val));
        back(data);
      },
      response => {
        console.log('失败');
      },
      () => {
        console.log('完成post');
      }
    );
  }

  register(name: string, passWord: string, email: string, phone: string, address: string, type: string, back: any) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/register', {
      name: name,
      passWord: passWord,
      email: email,
      phone: phone,
      address: address
    }, { headers: header }).subscribe(
      (val) => {
        const data = JSON.parse(JSON.stringify(val));
        back(data);
      },
      response => {
        console.log('失败');
      },
      () => {
        console.log('完成post');
      }
    );
  }

  getOrder (userId: string) {
    return this.http.get(this.storage.get(this.storage.baseUrl) + '/api/v1/message/' + userId);
  }
}
