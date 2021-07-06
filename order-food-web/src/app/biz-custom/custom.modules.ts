import { NgModule } from '@angular/core';
import { CustomRoutingModule } from './custom-routing.module';
// 共享模块 有ng-zorro
import { SharedModule } from 'app/shared/shared.module';

import { OrderFoodComponent } from './order-food.component';
import { MyInfoComponent } from './my-info.component';
import { MyMessageComponent } from './my-message.component';
import { MyOrderComponent } from './my-order.component';
import { OrderFoodDetailComponent } from './order-food-detail.component';
import { ShoppingCartComponent } from './shopping-cart.component';
import { FoodMenuComponent } from './food-menu.component';

@NgModule({
  imports: [
    CustomRoutingModule,
    SharedModule
  ],
  declarations: [
    OrderFoodComponent,
    MyInfoComponent,
    MyMessageComponent,
    MyOrderComponent,
    OrderFoodDetailComponent,
    ShoppingCartComponent,
    FoodMenuComponent
  ]
})

export class CustomModules {}
