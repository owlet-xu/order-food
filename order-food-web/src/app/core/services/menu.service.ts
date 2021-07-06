import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { SessionStorageService } from 'app/core/services/session-storage.service';

export class MenuItem {
  id: string;
  url: string;
  title: string;
  icon?: string;
  children?: MenuItem[];
}

@Injectable()
export class MenuService {
  constructor (private storageSession: SessionStorageService) { }

  getMenus(): Observable<MenuItem[]> {
    const userData = this.storageSession.getObject(this.storageSession.userData);
    let menus: any[];
    if (userData.type === '0') {
      menus = [{
        id: '1',
        url: '/custom/',
        title: 'menu_custom',
        icon: 'anticon anticon-shopping-cart',
        children: [
          { id: '11', url: '/home/custom/order-food', title: 'menu_order_food', icon: 'anticon anticon-shop' },
          { id: '12', url: '/home/custom/my-order', title: 'menu_my_order', icon: 'anticon anticon-copy' },
          // { id: '13', url: '/home/custom/my-message', title: 'menu_my_message', icon: 'anticon anticon-mail' },
          { id: '14', url: '/home/custom/my-info', title: 'menu_my_info', icon: 'anticon anticon-idcard' }
        ]
      }];
    } else if (userData.type === '1') {
      menus = [{
        id: '2',
        url: '/shop/',
        title: 'menu_shop',
        icon: 'anticon anticon-bank',
        children: [
          { id: '21', url: '/home/shop/food-manager', title: 'menu_food_manager', icon: 'anticon anticon-form' },
          { id: '22', url: '/home/shop/my-custom', title: 'menu_my_custom', icon: 'anticon anticon-usergroup-add' },
          { id: '23', url: '/home/shop/order-manager', title: 'menu_order_manager', icon: 'anticon anticon-schedule' },
          { id: '24', url: '/home/shop/my-shop', title: 'menu_my_shop', icon: 'anticon anticon-home' },
          // { id: '25', url: '/home/shop/send-message', title: 'menu_send_message', icon: 'anticon anticon-mail' },
          { id: '26', url: '/home/shop/shop-info', title: 'menu_shop_info', icon: 'anticon anticon-idcard' }
        ]
      }];
    } else if (userData.type === '2') {
      menus = [{
        id: '1',
        url: '/custom/',
        title: 'menu_custom',
        icon: 'anticon anticon-shopping-cart',
        children: [
          { id: '11', url: '/home/custom/order-food', title: 'menu_order_food', icon: 'anticon anticon-shop' },
          { id: '12', url: '/home/custom/my-order', title: 'menu_my_order', icon: 'anticon anticon-copy' },
          // { id: '13', url: '/home/custom/my-message', title: 'menu_my_message', icon: 'anticon anticon-mail' },
          { id: '14', url: '/home/custom/my-info', title: 'menu_my_info', icon: 'anticon anticon-idcard' }
        ]
      },
      {
        id: '2',
        url: '/shop/',
        title: 'menu_shop',
        icon: 'anticon anticon-bank',
        children: [
          { id: '21', url: '/home/shop/food-manager', title: 'menu_food_manager', icon: 'anticon anticon-form' },
          { id: '22', url: '/home/shop/my-custom', title: 'menu_my_custom', icon: 'anticon anticon-usergroup-add' },
          { id: '23', url: '/home/shop/order-manager', title: 'menu_order_manager', icon: 'anticon anticon-schedule' },
          { id: '24', url: '/home/shop/my-shop', title: 'menu_my_shop', icon: 'anticon anticon-home' },
          // { id: '25', url: '/home/shop/send-message', title: 'menu_send_message', icon: 'anticon anticon-mail' },
          { id: '26', url: '/home/shop/shop-info', title: 'menu_shop_info', icon: 'anticon anticon-idcard' }
        ]
      }];
    } else {
      menus = [];
    }

    return of(menus);
  }
}
