import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: 'custom',
        loadChildren: 'app/biz-custom/custom.modules#CustomModules',
        data: { preload: true }
      },
      {
        path: 'shop',
        loadChildren: 'app/biz-shop/shop.modules#ShopModules',
        data: { preload: true }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
