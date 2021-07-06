import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FoodManagerComponent } from './food-manager.component';
import { MyCustomComponent } from './my-custom.component';
import { MyShopComponent } from './my-shop.component';
import { OrderManagerComponent } from './order-manager.component';
import { SendMessageComponent } from './send-message.component';
import { ShopInfoComponent } from './shop-info.componnet';

const routers: Routes = [
  {path: '', component: OrderManagerComponent},
  {path: 'food-manager', component: FoodManagerComponent},
  {path: 'my-custom', component: MyCustomComponent},
  {path: 'my-shop', component: MyShopComponent},
  {path: 'order-manager', component: OrderManagerComponent},
  {path: 'send-message', component: SendMessageComponent},
  {path: 'shop-info', component: ShopInfoComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routers)],
  exports: [RouterModule]
})

export class ShopRoutingModule {}
