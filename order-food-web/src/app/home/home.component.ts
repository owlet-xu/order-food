import { Component, OnInit } from '@angular/core';
import { SessionStorageService } from 'app/core/services/session-storage.service';
import { Router } from '@angular/router';

@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  isCollapsed = false;

  constructor(private storageSession: SessionStorageService, private router: Router) { }
  ngOnInit () {
    if (!this.storageSession.isLogin()) {
      this.router.navigate(['login']);
    } else if (this.storageSession.getObject(this.storageSession.userData).type === '1') {
      this.router.navigate(['home/shop/my-shop']);
    } else {
      this.router.navigate(['home/custom/order-food']);
    }
  }
}
