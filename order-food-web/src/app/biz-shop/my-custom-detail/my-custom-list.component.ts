import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { ShopDataService } from 'app/core/services/shop-data.service';


@Component({
  selector: 'od-my-custom-list',
  templateUrl: './my-custom-list.component.html',
  styleUrls: ['./my-custom-list.component.scss']
})

export class MyCustomListComponent implements OnInit {
  @Output() private selectUserData = new EventEmitter<any>();
  // 搜索框
  validateForm: FormGroup;
  controlArray = []; // 表单的conrol
  isCollapse = true;
  // 分页
  pageIndex = 1;
  pageSize = 10;
  total = 1;
  dataSet = [];
  loading = false;
  sortke = null;

  constructor(
    private fb: FormBuilder,
    private shopService: ShopDataService
  ) { }

  ngOnInit() {
    // 搜索表单初始化
    this.validateForm = this.fb.group({});
    this.controlArray.push({ index: 1, show: true, title: '姓名', placeholder: '请输入姓名', type: 'text' });
    this.validateForm.addControl(`field1`, new FormControl());
    this.controlArray.push({ index: 2, show: true, title: '手机号', placeholder: '请输入手机号', type: 'number' });
    this.validateForm.addControl(`field2`, new FormControl());
    this.loadList();
  }
  search() {
    this.pageIndex = 1;
    this.loadList();
  }
  loadList() {
    const { field1, field2 } = this.validateForm.getRawValue();
    this.dataSet = [];
    this.total = 1;
    this.shopService.getMyCustom(field1, field2, this.pageIndex, this.pageSize, (data) => {
      if (data.success) {
        this.dataSet = data.data.content;
        this.total = data.data.total;
        this.selectUserData.emit(this.dataSet[0]);
      }
    });
  }
  resetForm(): void {
    this.validateForm.reset();
  }
  toInfo(userItem: any) {
    this.selectUserData.emit(userItem);
  }
  getSexName (val) {
    if (val === '1') {
      return '女';
    } else {
      return '男';
    }
  }
}
