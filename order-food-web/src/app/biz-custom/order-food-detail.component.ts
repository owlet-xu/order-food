import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GusetService } from '../core/services/gusest.services';
import { ActivatedRoute, Params } from '@angular/router';
import { LocalStorageService } from 'app/core/services/local-storage.service';
import { ShopDataService } from 'app/core/services/shop-data.service';

@Component({
  selector: 'od-order-food-detail',
  templateUrl: './order-food-detail.component.html',
  styleUrls: ['./order-food-detail.component.scss']
})

export class OrderFoodDetailComponent implements OnInit {

  data: any;
  assess: any;
  baseImgUrl: string;
  baseUrl: string;
  foodId: string;
  List = [];



  constructor(
    private shopData: ShopDataService,
    private gusetservice: GusetService,
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
    for (let index = 0; index < this.gusetservice.guest.length; index++) {
      const element = this.gusetservice.guest[index];
      if (element.id === this.data.id) {
        element.count++;
        element.total = element.count * element.discountPrice;
        this.gusetservice.total += element.discountPrice * 1;
        this.gusetservice.guest[index] = element;
        isHave = true;
        break;
      }
    }
    if (!isHave) {
      this.gusetservice.guest.unshift({
        id: this.data.id,
        foodName: this.data.foodName,
        discountPrice: this.data.discountPrice,
        count: 1,
        total: this.data.discountPrice,
        type: this.data.foodType,
        picture: this.data.picture
      });
      this.gusetservice.total += this.data.discountPrice * 1;
     /*  console.log(this.gusetservice.total); */
    }
  }


 ngOnInit() {
   this.shopData.fooddetail(this.foodId, (res: any) => {
    const back = JSON.parse(JSON.stringify(res));
    if (back.success) {
      this.data = back.data;
      this.List.push(
        {pic: this.data.pictureSon1},
        {pic: this.data.pictureSon2},
        {pic: this.data.pictureSon3});
    }
   });
  }
  getComments () {
    this.shopData.getComments(this.foodId, (res: any) => {
      const ass = JSON.parse(JSON.stringify(res));
      if (ass.success) {
        this.assess = ass.data;
      }
    })
  }
  likeNumberToInt (item) {
    return item * 1;
  }
}
