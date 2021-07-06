import { Component, OnInit } from '@angular/core';
import { MyToolsService } from 'app/core/services/my-tools.service';
import { Router } from '@angular/router';
import { SessionStorageService } from 'app/core/services/session-storage.service';

@Component({
  selector: 'od-notify',
  templateUrl: './notify.component.html',
  styleUrls: ['./notify.component.scss']
})

export class NotifyComponent implements OnInit {

  orders: any;
  count: number;
  userData: any;

  constructor (
    private tools: MyToolsService,
    private router: Router,
    private storageSession: SessionStorageService
  ) {}

  ngOnInit () {
    this.tools.getMessage().subscribe(data => {
      // console.log(data);
      if (data.success) {
        this.count = data.data.countNumber;
        this.orders = data.data.listInfoList;
      }
    });
    this.userData = this.storageSession.getObject(this.storageSession.userData);
  }

  toOrders () {
    if (this.userData.type === '0') {
      this.router.navigate(['/home/custom/my-order']);
    } else  if (this.userData.type === '1') {
      this.router.navigate(['/home/shop/order-manager']);
    }
  }
}
