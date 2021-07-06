import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LocalStorageService } from 'app/core/services/local-storage.service';
// import 'rxjs/operator/map';

@Injectable()

export class CustomDataService {

  constructor(private http: HttpClient, private storage: LocalStorageService) { }

  addEvaluate(content: string, evaluateId: string, evaluateName: string, likeLevle: string, orderId: string) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    return this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/comment', {
      content: content,
      evaluateId: evaluateId,
      evaluateName: evaluateName,
      likeLevle: likeLevle,
      orderId: orderId
    }, { headers: header });
  }

  editUser (
    address: string,
    age: string,
    email: string,
    headImg: string,
    interest: string,
    name: string,
    passWord: string,
    phone: string,
    sex: string,
    type: string
  ) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    return this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/comment', {
      address: address,
    age: age,
    email: email,
    headImg: headImg,
    interest: interest,
    name: name,
    passWord: passWord,
    phone: phone,
    sex: sex,
    type: type
    }, { headers: header });
  }

  getMyOrders (num: string, name: string, beginTime: string, endTime: string,
    paSize: number, pageNum: number, userId: string, type: string) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    return this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/findOrderList', {
      num: num, name: name, beginTime: beginTime, endTime: endTime, paSize: paSize, pageNum: pageNum,
      userId: userId, type: type
    }, { headers: header });
  }

  getOrderFoodByOrderId (orderId: string) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    return this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/searchorder', {
      id: orderId
    }, { headers: header });
  }

  deleteOrder(orderId: string) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    return this.http.get(this.storage.get(this.storage.baseUrl) + '/api/v1/updateOrder/' + orderId);
  }
}
