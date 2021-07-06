import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { OrderFoodComponent } from './order-food.component';
import { MyInfoComponent } from './my-info.component';
import { MyMessageComponent } from './my-message.component';
import { MyOrderComponent } from './my-order.component';
import { OrderFoodDetailComponent } from './order-food-detail.component';
import { FoodMenuComponent } from './food-menu.component';


const routes: Routes = [
  { path: '', component: FoodMenuComponent },
  {
    path: 'order-food',
    component: FoodMenuComponent,
  },
  {
    path: 'order-food/:id',
    component: OrderFoodDetailComponent,
  },
  { path: 'my-info', component: MyInfoComponent },
  { path: 'my-message', component: MyMessageComponent },
  { path: 'my-order', component: MyOrderComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomRoutingModule {}
