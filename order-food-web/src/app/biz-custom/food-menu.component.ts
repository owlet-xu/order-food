import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GusetService } from '../core/services/gusest.services';

@Component({
  selector: 'od-food-menu',
  templateUrl: './food-menu.component.html',
  styleUrls: ['./food-menu.component.scss']
})

export class FoodMenuComponent implements OnInit {


/*   food: any;
  dataToChild: 10;
  getData(event) {
    this.food = event;
  }  */

  constructor (private router: Router, private service: GusetService) {
  }
  ngOnInit () {

  }
}
