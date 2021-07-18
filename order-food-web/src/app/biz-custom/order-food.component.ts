import { Component, Output, OnInit, EventEmitter, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { GusetService } from '../core/services/gusest.services';
import { LocalStorageService } from 'app/core/services/local-storage.service';
import { SessionStorageService } from 'app/core/services/session-storage.service';
import { ShopDataService } from 'app/core/services/shop-data.service';

@Component({
  selector: 'od-order-food',
  templateUrl: './order-food.component.html',
  styleUrls: ['./order-food.component.scss'],
})

export class OrderFoodComponent implements AfterViewInit {
  testSwiper: Swiper;
  signal: string;
  data: any;
  data2: any;
  data3: any;
  data4: any;
  foodtp: any;
  maxPrice: any;
  minPrice: any;
  paSize: any;
  pageNum: any;

  foodDetail: any;
  foodList: any;
  addPrice: any;
  totalPrice: any;
  baseImgUrl: string;
  List = [];
  isVisible = false;

  constructor(
    public gusetS: GusetService,
    private router: Router,
    private shopData: ShopDataService,
    private storage: LocalStorageService,
    private storageSession: SessionStorageService
  ) {
    this.baseImgUrl = this.storage.get(this.storage.baseImgUrl);
  }



  /* 组件通信
  @Output() event = new EventEmitter();*/

  add(item): void {
    let isHave = false;
    for (let index = 0; index < this.gusetS.guest.length; index++) {
      const element = this.gusetS.guest[index];
      if (element.id === item.id) {
        element.count++;
        element.total = element.count * element.discountPrice;
        this.gusetS.total += element.discountPrice * 1;
        this.gusetS.guest[index] = element;
        isHave = true;
        break;
      }
    }
    if (!isHave) {
      this.gusetS.guest.unshift({
        id: item.id,
        foodName: item.foodName,
        discountPrice: item.discountPrice,
        count: 1,
        total: item.discountPrice,
        type: item.foodType,
        picture: item.picture
      });
      this.gusetS.total += item.discountPrice * 1;
    }
  }

  ngAfterViewInit() {
    /* 模拟数据格式 */
    /* this.foodList = [
      {id: 1 , foodName: '红烧肉', price: 15 , discountPrice: 13, foodType: '微辣', remarks: '精选五花肉，上等的好食材',
       picList : [
         {pic: '../../../assets/images/images1.jpg'},
         {pic: '../../../assets/images/images2.jpg'},
         {pic: '../../../assets/images/images3.jpg'}]
       },
      {id: 2 , foodName: '小鸡炖蘑菇', price: 20 , discountPrice: 18, foodType: '不辣', remarks: '精选五花肉，上等的好食材',
      picList : [
        {pic: '../../../assets/images/images2.jpg'},
        {pic: '../../../assets/images/images2.jpg'},
        {pic: '../../../assets/images/images3.jpg'}]} ,
      {id: 3 , foodName: '水煮鱼', price: 21 , discountPrice: 19, foodType: '特辣', remarks: '精选五花肉，上等的好食材',
      picList : [
        {pic: '../../../assets/images/images3.jpg'},
        {pic: '../../../assets/images/images2.jpg'},
        {pic: '../../../assets/images/images3.jpg'}]} ,
      {id: 4 , foodName: '鱼香肉丝', price: 16 , discountPrice: 15, foodType: '不辣', remarks: '精选五花肉，上等的好食材',
      picList : [
        {pic: '../../../assets/images/images4.jpg'},
        {pic: '../../../assets/images/images2.jpg'},
        {pic: '../../../assets/images/images3.jpg'}]} ,
      {id: 5 , foodName: '韭菜炒蛋', price: 17 , discountPrice: 15, foodType: '微辣', remarks: '精选五花肉，上等的好食材',
      picList : [
        {pic: '../../../assets/images/images5.jpg'},
        {pic: '../../../assets/images/images2.jpg'},
        {pic: '../../../assets/images/images3.jpg'}]} ,
      {id: 6 , foodName: '炒三丝', price: 18 , discountPrice: 16, foodType: '不辣', remarks: '精选五花肉，上等的好食材',
      picList : [
        {pic: '../../../assets/images/images6.jpg'},
        {pic: '../../../assets/images/images2.jpg'},
        {pic: '../../../assets/images/images3.jpg'}]} ,
      {id: 7 , foodName: '地三鲜', price: 19 , discountPrice: 17, foodType: '辣', remarks: '精选五花肉，上等的好食材',
      picList : [
        {pic: '../../../assets/images/images7.jpg'},
        {pic: '../../../assets/images/images2.jpg'},
        {pic: '../../../assets/images/images3.jpg'}]} ,
      {id: 8 , foodName: '水煮肉片', price: 20 , discountPrice: 18, foodType: '不辣', remarks: '精选五花肉，上等的好食材',
      picList : [
        {pic: '../../../assets/images/images8.jpg'},
        {pic: '../../../assets/images/images2.jpg'},
        {pic: '../../../assets/images/images3.jpg'}]} ,
    ]; */
    const userData = this.storageSession.getObject(this.storageSession.userData);
    this.shopData.searchgaijiaofan(0, userData.type, (res) => {
      this.data = JSON.parse(JSON.stringify(res)).data.content;
      this.List.push(
        { id: this.data[0].id, pic: this.data[0].picture, foodName: this.data[0].foodName }
      );
    });

    this.shopData.searchgaijiaofan(1, userData.type, (res) => {
      this.data2 = JSON.parse(JSON.stringify(res)).data.content;
      this.List.push(
        { id: this.data2[0].id, pic: this.data2[0].picture, foodName: this.data2[0].foodName }
      );
    });
    this.shopData.searchgaijiaofan(2, userData.type, (res) => {
      this.data3 = JSON.parse(JSON.stringify(res)).data.content;
      this.List.push(
        { id: this.data3[0].id, pic: this.data3[0].picture, foodName: this.data3[0].foodName }
      );
    });

    this.shopData.searchgaijiaofan(3, userData.type, (res) => {
      this.data4 = JSON.parse(JSON.stringify(res)).data.content;
      this.List.push(
        { id: this.data4[0].id, pic: this.data4[0].picture, foodName: this.data4[0].foodName }
      );
    });

    this.testSwiper = new Swiper('.swiper-container', {
      direction: 'horizontal',
      autoplay: {
        delay: 3000,
        stopOnLastSlide: false,
      },
      speed: 1000,
      observer: true,
      observeParents: true,
      pagination: {
        el: '.swiper-pagination',
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      }
    });

  }

  showModal(item): void {
    this.router.navigate(['home/custom/order-food/' + item.id]);
  }
  toFoodInfo(item): void {
    /* console.log(item.id); */
    this.router.navigate(['home/custom/order-food/' + item.id]);
  }
}
