import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PreloadStrategy } from './core/services/preload-strategy';

const routes: Routes = [
  {
    path: '',
    loadChildren: 'app/login/login.module#LoginModule'
  },
  {
    path: 'home',
    loadChildren: 'app/home/home.module#HomeModule'
  },
  {
    path: 'login',
    loadChildren: 'app/login/login.module#LoginModule'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      useHash: true,
      preloadingStrategy: PreloadStrategy
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
