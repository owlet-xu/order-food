import { Component, Input } from '@angular/core';

import { MenuItem } from 'app/core/services/menu.service';

@Component({
  selector: 'od-menu-item',
  templateUrl: 'menu-item.component.html'
})
export class MenuItemComponent {

  @Input() menuItem: MenuItem;
}
