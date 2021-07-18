import { Component, OnInit } from '@angular/core';
import { GusetService } from '../core/services/gusest.services';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { SessionStorageService } from 'app/core/services/session-storage.service';
import { NzMessageService } from 'ng-zorro-antd';
import { LocalStorageService } from 'app/core/services/local-storage.service';
import { ShopDataService } from 'app/core/services/shop-data.service';

@Component({
  selector: 'od-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss'],
  animations: [
    trigger('signal', [
      state('in', style({
        'transform': 'translateX(100%)'
      })),
      state('stop', style({
        'transform': 'translateX(0)'
      })),
      transition('* => *', animate(200))
    ])
  ]
})
export class ShoppingCartComponent implements OnInit {
  // @Input() private data: any;
  signal = 'in';
  isVisible = false;
  arr = [];
  obj: any;
  totalPrice: any;
  foodName: any;
  orderId: any;
  price: 0;
  shopId: any;
  userId: any;
  userData: any;
  type: any;
  guestData: any;
  inputValue: any;
  orderRemark: any;
  baseImgUrl: string;

  toggle() {
    if (this.signal === 'in') {
      this.signal = 'stop';
    } else {
      this.signal = 'in';
    }
  }


  constructor(
    public service: GusetService,
    private shopData: ShopDataService,
    private storageSession: SessionStorageService,
    private message: NzMessageService,
    private storage: LocalStorageService
  ) {
    this.userData = this.storageSession.getObject(this.storageSession.userData);
    this.baseImgUrl = this.storage.get(this.storage.baseImgUrl);
    this.service.guest = [];
    this.service.total = 0;
    console.log(this.service.total);
  }
  inc(item) {
    for (let index = 0; index < this.service.guest.length; index++) {
      const element = this.service.guest[index];
      if (element.id === item.id) {
        element.count++;
        element.total = element.count * element.discountPrice;
        this.service.total += element.discountPrice * 1;
        this.service.guest[index] = element;
        break;
      }
    }
  }
  dec(item) {
    for (let index = 0; index < this.service.guest.length; index++) {
      const element = this.service.guest[index];
      if (element.id === item.id) {
        element.count--;
        element.total = element.count * element.discountPrice;
        this.service.total -= element.discountPrice * 1;
        this.service.guest[index] = element;
        if (element.count === 0) {
          this.service.guest.splice(index, 1);
        }
        break;
      }
    }
  }

  /* 初始化判断屏幕的宽度 */
  ngOnInit() {
    if (window.innerWidth > 414) {
      this.signal = 'stop';
    } else {
      this.signal = 'in';
    }
  }
  handleOk(item: any): void {
    this.totalPrice = this.service.total;
    this.service.guest.forEach(element => {
      this.obj = {
        count: element.count,
        foodId: element.id,
        foodName: element.foodName,
        price: element.discountPrice,
        type: element.type
      };
      this.arr.push(this.obj);
    });

    this.shopData.createOrderInfo(
      this.userData.id,
      this.userData.address,
      this.userData.name,
      this.userData.phone,
      this.totalPrice,
      this.inputValue,
      this.type,
      this.arr,
      (res) => {
        debugger;
        const data = JSON.parse(JSON.stringify(res));
        if (data.success) {
          this.message.success('添加订单成功');
        } else {
          this.message.error(data.msg);
        }
        this.service.guest = [];
        this.service.total = 0;
      });
    this.isVisible = false;
  }
  handleCancel(): void {
    this.isVisible = false;
  }
  submit() {
    this.isVisible = true;
    /* console.log(this.service.total);
    console.log(this.service.guest); */
  }
}
