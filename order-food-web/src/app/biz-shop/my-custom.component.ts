import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'od-my-custom',
  templateUrl: './my-custom.component.html',
  styleUrls: ['./my-custom.component.scss']
})

export class MyCustomComponent implements OnInit {

public selectUserData: any;

constructor () {
  this.selectUserData = {};
 }

ngOnInit () { }

getUserDataFromChild (event) {
  this.selectUserData = event;
  console.log(this.selectUserData.headImg);
}
}
