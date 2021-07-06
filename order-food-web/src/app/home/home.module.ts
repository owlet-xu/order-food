import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { HeaderComponent } from './header/header.component';
import { UserComponent } from './header/user.component';
import { SiderComponent } from './sider/sider.component';
import { MenuItemComponent } from './sider/menu-item/menu-item.component';
import { NotifyComponent } from './header/notify.component';

@NgModule({
  imports: [
    SharedModule,
    HomeRoutingModule
  ],
  declarations: [
    HomeComponent,
    HeaderComponent,
    SiderComponent,
    MenuItemComponent,
    UserComponent,
    NotifyComponent
  ]
})
export class HomeModule {}

