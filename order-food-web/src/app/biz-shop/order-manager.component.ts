import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { NzModalService } from 'ng-zorro-antd';
import { NzMessageService } from 'ng-zorro-antd';
import { ShopDataService } from 'app/core/services/shop-data.service';
import { SessionStorageService } from 'app/core/services/session-storage.service';
import { MyToolsService } from 'app/core/services/my-tools.service';
@Component({
  selector: 'od-order-manager',
  templateUrl: './order-manager.component.html',
  styleUrls: ['./order-manager.component.scss']
})

export class OrderManagerComponent implements OnInit {
   // 分页表格数据
   pageIndex = 1;
   pageSize = 5;
   total = 1;
   nestedTableData = [];
   loading = false;
  // 搜索框
  validateForm: FormGroup;
  controlArray = [];
  controlArray2 = [];
  // 确认模态框
  isShowDeleteConfirm = false;
  deleteConfirmTitle = '';
  deleteReason = '非常抱歉，您的订餐已售空';
  orderId: string;
  orderNum: string;



  constructor (
    private fb: FormBuilder,
    private modalService: NzModalService,
    private message: NzMessageService,
    private shopData: ShopDataService,
    private storageSession: SessionStorageService,
    private myTools: MyToolsService
    ) {}

  resetForm(): void {
    this.validateForm.reset();
  }

  ngOnInit(): void {
  // 搜索表单初始化
  this.validateForm = this.fb.group({});
  this.controlArray.push({ index: 1, show: true, title: '订单号', placeholder: '请输入订单号', type: 'number', domType: 'input' });
  this.validateForm.addControl(`field1`, new FormControl());
  this.controlArray.push({ index: 2, show: true, title: '客户姓名', placeholder: '请输入客户姓名', type: 'text', domType: 'input' });
  this.validateForm.addControl(`field2`, new FormControl());
  this.controlArray.push({ index: 3, show: true, title: '客户地址', placeholder: '请输入客户地址', type: 'text', domType: 'input' });
  this.validateForm.addControl(`field3`, new FormControl());
  this.controlArray.push({ index: 4, show: true, title: '订单状态', placeholder: '请选择订单状态', type: 'text', domType: 'select' });
  this.validateForm.addControl(`field4`, new FormControl());


  this.controlArray2.push({ index: 5, show: true, title: '开始时间', placeholder: '请输入开始时间', type: 'text', domType: 'datetime' });
  this.validateForm.addControl(`field5`, new FormControl());
  this.controlArray2.push({ index: 6, show: true, title: '结束时间', placeholder: '请输入结束时间', type: 'text', domType: 'datetime' });
  this.validateForm.addControl(`field6`, new FormControl());
  this.loadData();
  }
  loadData () {
    this.nestedTableData = [];
    const {field1, field2, field3, field4, field5, field6} = this.validateForm.getRawValue();
    let startTime;
    let endTime;
    if ( null !== field5 && null !== field6) {
      // if (field5.getTime() < field6.getTime()) {
      //   return;
      // }
      startTime = this.myTools.DateFormate(field5, 'yyyy-MM-dd hh:mm:ss');
      endTime = this.myTools.DateFormate(field6, 'yyyy-MM-dd hh:mm:ss');
    }
    const userData = this.storageSession.getObject(this.storageSession.userData);
    this.shopData.getMyOrders(field1, field2, field3, field4, startTime, endTime, this.pageSize, this.pageIndex,
      userData.id, userData.type).subscribe(res => {
      const data = JSON.parse(JSON.stringify(res));
      if (data.success) {
        this.nestedTableData = data.data.orderDetailListList;
        this.total = data.data.total;
      }
    });
  }
  searchData () {
    this.pageIndex = 1;
    this.loadData();
  }
  showDeleteConfirm (id: string, num: string) {
    this.deleteConfirmTitle = '是否拒接' + num + '订单？';
    this.isShowDeleteConfirm = true;
    this.orderId = id;
    this.orderNum = num;
  }
  deleteConfirmDo(isOk: boolean) {
    if (isOk) {
      this.shopData.refuseOrder(this.orderId, this.deleteReason, '').subscribe(res => {
        const data = JSON.parse(JSON.stringify(res));
        if (data.success) {
          this.loadData();
          this.isShowDeleteConfirm = false;
          this.message.create('success', `订单${this.orderNum}已拒接`);
        }
      });
    }
  }
  // 接单
  showAddConfirm (id: string, num: string) {
    this.shopData.recieveOrder(id, '').subscribe(res => {
      const data = JSON.parse(JSON.stringify(res));
      if (data.success) {
        this.message.create('success', `订单${num}接单成功`);
        this.loadData();
      }
    });
   }
   getStatusName (status) {
    switch (status) {
      case '0':
      return '用户取消';
      case '1':
      return '等待接单';
      case '2':
      return '已接单';
      case '3':
      return '商家取消';
      default:
      return '';
    }
  }

}
