import { NgModule } from '@angular/core';
// 共享模块 有ng-zorro
import { SharedModule } from 'app/shared/shared.module';
import { ShopRoutingModule } from './shop-routing.module';
// echarts 模块的导入
import { NgxEchartsModule } from 'ngx-echarts';

// 页面
import { FoodManagerComponent } from './food-manager.component';
import { MyCustomComponent } from './my-custom.component';
import { MyShopComponent } from './my-shop.component';
import { OrderManagerComponent } from './order-manager.component';
import { SendMessageComponent } from './send-message.component';
import { ShopInfoComponent } from './shop-info.componnet';
import { MycustomDetailComponent } from './my-custom-detail/my-custom-detail.component';
import { MyCustomListComponent } from './my-custom-detail/my-custom-list.component';

@NgModule({
  imports: [
    SharedModule,
    ShopRoutingModule,
    NgxEchartsModule
  ],
  declarations: [
    FoodManagerComponent,
    MyCustomComponent,
    MyShopComponent,
    OrderManagerComponent,
    SendMessageComponent,
    ShopInfoComponent,
    MycustomDetailComponent,
    MyCustomListComponent
  ]
})

export class ShopModules {}
