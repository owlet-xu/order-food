import { Component, OnInit } from '@angular/core';

import { MenuService, MenuItem } from 'app/core/services/menu.service';

@Component({
  selector: 'od-sider',
  templateUrl: './sider.component.html',
  styleUrls: ['./sider.component.scss']
})
export class SiderComponent implements OnInit {

  menus: MenuItem[];

  constructor(
    private menuService: MenuService
  ) {}

  ngOnInit(): void {
    this.menuService.getMenus().subscribe(menus => {
      this.menus = menus;
    });
  }
}
