import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GusetService } from '../core/services/gusest.services';
import { ActivatedRoute, Params } from '@angular/router';
import { LocalStorageService } from 'app/core/services/local-storage.service';

@Component({
  selector: 'od-order-food-detail',
  templateUrl: './order-food-detail.component.html',
  styleUrls: ['./order-food-detail.component.scss']
})

export class OrderFoodDetailComponent implements OnInit {

  data: any;
  assess: any;
  baseImgUrl: string;
  foodId: string;
  List = [];



  constructor(
    private http: HttpClient,
    private service: GusetService,
    private storage: LocalStorageService,
    private route: ActivatedRoute
  ) {
    this.baseImgUrl = this.storage.get(this.storage.baseImgUrl);
    this.route.params.subscribe((params: Params) => {
      this.foodId = params.id;
    });
    this.getComments();
  }


  add(): void {
    if (!this.data) {
      alert('获取菜单失败，请刷新页面');
      return;
    }
    let isHave = false;
    for (let index = 0; index < this.service.guest.length; index++) {
      const element = this.service.guest[index];
      if (element.id === this.data.id) {
        element.count++;
        element.total = element.count * element.discountPrice;
        this.service.total += element.discountPrice * 1;
        this.service.guest[index] = element;
        isHave = true;
        break;
      }
    }
    if (!isHave) {
      this.service.guest.unshift({
        id: this.data.id,
        foodName: this.data.foodName,
        discountPrice: this.data.discountPrice,
        count: 1,
        total: this.data.discountPrice,
        type: this.data.foodType,
        picture: this.data.picture
      });
      this.service.total += this.data.discountPrice * 1;
     /*  console.log(this.service.total); */
    }
  }


 ngOnInit() {
    let header: HttpHeaders;
    header = new HttpHeaders();

    header.append('Content-Type', 'application/json');
    this.http.get('http://172.19.14.22:8666/api/v1/fooddetail/' +  this.foodId)
    .subscribe(
      val => {
        const back = JSON.parse(JSON.stringify(val));
        if (back.success) {
          this.data = back.data;
          this.List.push(
            {pic: this.data.pictureSon1},
            {pic: this.data.pictureSon2},
            {pic: this.data.pictureSon3});
        }
      },
      respone => {
        /* console.log('失败'); */
      },
      () => {
        /* console.log('完成get'); */
      }
    );
  }
  getComments () {
    let header: HttpHeaders;
    header = new HttpHeaders();
    header.append('Content-Type', 'application/json');
    this.http.get('http://172.19.14.22:8666/api/v1/commentlist/' + this.foodId)
    .subscribe(
      val => {
        const ass = JSON.parse(JSON.stringify(val));
        if (ass.success) {
          this.assess = ass.data;
        }
      },
      respone => {
       /*  console.log('失败'); */
      },
      () => {
       /*  console.log('完成get'); */
      }
    );
  }
  likeNumberToInt (item) {
    return item * 1;
  }
}
