import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LocalStorageService } from 'app/core/services/local-storage.service';
import { FoodInfo } from 'app/models/food-info';
import { UserInfo } from 'app/models/user-info';

@Injectable()
export class ShopDataService {

  constructor(private http: HttpClient, private storage: LocalStorageService) { }

  getFoodList(foodName: string, minPrice: number, maxPrice: number, foodType: number, status: number, back: any) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/searchfood', {
      foodName: foodName,
      minPrice: minPrice,
      maxPrice: maxPrice,
      foodType: foodType,
      status: status
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

  addFood(
    food: FoodInfo,
    back: any
  ) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/addFood', {
      ...food
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

  foodActive(id: string, status: string, back: any) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/updatestatus', {
      id: id,
      status: status
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

  foodDelete(id: string, back: any) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    this.http.delete(this.storage.get(this.storage.baseUrl) + '/api/v1/delete/' + id).subscribe(
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

  getMyCustom(name: string, phone: string, pageIndex: number, pageSize: number, back: any) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/searchuser', {
      name: name,
      phone: phone,
      pageNum: pageIndex,
      paSize: pageSize
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
  getMyOrders(num: string, name: string, address: string,
    status: string, beginTime: string, endTime: string, paSize: number, pageNum: number,
    userId: string, type: string) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    return this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/findOrderList', {
      num: num, name: name, address: address, status: status, beginTime: beginTime, endTime: endTime, paSize: paSize, pageNum: pageNum,
      userId: userId, type: type
    }, { headers: header });
  }
  refuseOrder(orderId: string, sellerRemark: string, userId: string) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    return this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/sellerUpdate', {
      orderId: orderId, sellerRemark: sellerRemark, status: '3'
    }, { headers: header });
  }
  recieveOrder(orderId: string, userId: string) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    return this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/sellerUpdate', {
      orderId: orderId, status: '2'
    }, { headers: header });
  }
  editUser(
    user: UserInfo
  ) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    return this.http.post(this.storage.get(this.storage.baseUrl) + '/api/v1/update', {
      ...user
    }, { headers: header });
  }
  // 获取今日或本月订单数
  getCount(type: string) {
    return this.http.get(this.storage.get(this.storage.baseUrl) + '/api/v1/countNumber/' + type);
  }
  // 获取今日或本月金额
  getMoney(type: string) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    return this.http.get(this.storage.get(this.storage.baseUrl) + '/api/v1/countMoney/' + type);
  }
  // 获取今日订单和数目统计，分类型
  getTodaySum() {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    return this.http.get(this.storage.get(this.storage.baseUrl) + '/api/v1/countToday');
  }

  fooddetail(foodId: string, back) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    const url = `${this.storage.get(this.storage.baseUrl)}/api/v1/fooddetail/${foodId}`;
    this.http.get(url).subscribe(
      val => {
        back(val);
      },
      respone => {
        /* console.log('失败'); */
      },
      () => {
        /* console.log('完成get'); */
      }
    );
  }

  getComments(foodId: string, back: any) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    const url = `${this.storage.get(this.storage.baseUrl)}/api/v1/commentlist/${foodId}`;
    this.http.get(url).subscribe(
      val => {
        back(val);
      },
      respone => {
        /*  console.log('失败'); */
      },
      () => {
        /*  console.log('完成get'); */
      }
    );
  }

  createOrderInfo(orderId: any, orderAddress: any, orderName: any, orderPhone: any, totalPrice, orderRemark: any, type: any, orderMeanListInfoList: any, back: any) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    const url = `${this.storage.get(this.storage.baseUrl)}/api/v1/createOrderInfo`;
    this.http.post(
      url,
      {
        'orderId': orderId,
        'orderAddress': orderAddress,
        'orderName': orderName,
        'orderPhone': orderPhone,
        'totalPrice': totalPrice,
        'orderRemark': orderRemark,
        'type': type,
        'orderMeanListInfoList': orderMeanListInfoList
      },
      { headers: header }
    )
      .subscribe(
        val => {
          back(val);
        },
        respone => {
          /*  console.log('失败'); */
        },
        () => {
          /*  console.log('完成post'); */
        }
      );
  }

  searchgaijiaofan(foodType: number, userType: any, back: any) {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    const url = `${this.storage.get(this.storage.baseUrl)}/api/v1/searchgaijiaofan`;
    this.http.post(
      url,
      {
        foodType: foodType,
        maxPrice: 0,
        minPrice: 0,
        paSize: 0,
        pageNum: 0,
        userType: userType
      },
      { headers: header }
    )
      .subscribe(
        val => {
          back(val);
        },
        respone => {
          /*  console.log('失败'); */
        },
        () => {
          /* console.log('完成post'); */
        }
      );
  }
}
