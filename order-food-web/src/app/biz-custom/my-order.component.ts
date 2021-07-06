import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { NzModalService } from 'ng-zorro-antd';
import { CustomDataService } from 'app/core/services/custom-data.service';
import { SessionStorageService } from 'app/core/services/session-storage.service';
import { MyToolsService } from 'app/core/services/my-tools.service';
import { NzMessageService } from 'ng-zorro-antd';


@Component({
  selector: 'od-my-order',
  templateUrl: './my-order.component.html',
  styleUrls: ['./my-order.component.scss']
})

export class MyOrderComponent implements OnInit {
  // 搜索框
  validateForm: FormGroup;
  controlArray = [];
  // 评价模态框
  isShowEvaluate = false;
  evaluateContent = '';
  evaluateTitle = '';
  evaluateStars = '0';
  evaluateOrderId = '';
  // 分页
  pageIndex = 1;
  pageSize = 5;
  total = 1;
  nestedTableData = [];
  loading = false;


  constructor(private fb: FormBuilder,
    private modalService: NzModalService,
    private cusDataService: CustomDataService,
    private storageSession: SessionStorageService,
    private myTools: MyToolsService,
    private message: NzMessageService
  ) { }

  resetForm(): void {
    this.validateForm.reset();
  }

  ngOnInit(): void {
    // 搜索表单初始化
    this.validateForm = this.fb.group({});
    this.validateForm = this.fb.group({});
    this.controlArray.push({ index: 1, show: true, title: '订单号', placeholder: '请输入订单号', type: 'number', domType: 'input' });
    this.validateForm.addControl(`field1`, new FormControl());
    this.controlArray.push({ index: 2, show: true, title: '客户姓名', placeholder: '请输入客户姓名', type: 'text', domType: 'input' });
    this.validateForm.addControl(`field2`, new FormControl());
    this.controlArray.push({ index: 3, show: true, title: '开始时间', placeholder: '请输入开始时间', type: 'text', domType: 'datetime' });
    this.validateForm.addControl(`field3`, new FormControl());
    this.controlArray.push({ index: 4, show: true, title: '结束时间', placeholder: '请输入结束时间', type: 'text', domType: 'datetime' });
    this.validateForm.addControl(`field4`, new FormControl());
    this.loadData();
  }
  loadData () {
    this.nestedTableData = [];
    const {field1, field2, field3, field4} = this.validateForm.getRawValue();
    let startTime;
    let endTime;
    if ( null !== field3 && null !== field4) {
      if (field4.getTime() < field3.getTime()) {
        return;
      }
      startTime = this.myTools.DateFormate(field3, 'yyyy-MM-dd hh:mm:ss');
      endTime = this.myTools.DateFormate(field4, 'yyyy-MM-dd hh:mm:ss');
    }
    const userData = this.storageSession.getObject(this.storageSession.userData);
    this.cusDataService.getMyOrders(field1, field2, field3, field4, this.pageSize, this.pageIndex,
      userData.id, userData.type).subscribe(res => {
      console.log(res);
      const data = JSON.parse(JSON.stringify(res));
      if (data.success) {
        this.nestedTableData = data.data.orderDetailListList;
        this.total = data.data.total;
      }
    });
  }
  showDeleteConfirm(id: string, num: string) {
    this.modalService.confirm({
      nzTitle: '确认取消订单?',
      nzContent: '<b style="color: red;">是否取消订单号' + num + '的订单</b>',
      nzOkText: '取消订单',
      nzOkType: '取消订单',
      nzOnOk: () => {
        // console.log('OK');
        this.cusDataService.deleteOrder(id).subscribe(res => {
          const data = JSON.parse(JSON.stringify(res));
          if (data.success) {
            alert('取消成功');
            this.loadData();
          }
        });
      },
      nzCancelText: '放弃操作',
      nzOnCancel: () => console.log('Cancel')
    });
  }

  showEvaluate(id: string, num: string) {
    this.evaluateOrderId = id;
    this.evaluateTitle = '评价订单号' + num + '的订单';
    this.isShowEvaluate = true;
  }
  evaluateDo(isDo: boolean) {
    this.isShowEvaluate = false;
    if (!isDo) {
      return;
    }
    const userData = this.storageSession.getObject(this.storageSession.userData);
    this.cusDataService.addEvaluate(this.evaluateContent,
      userData.id, userData.name, this.evaluateStars, this.evaluateOrderId).subscribe(res => {
        const data = JSON.parse(JSON.stringify(res));
        if (data.success) {
          this.evaluateStars = '0';
          this.evaluateContent = '';
          this.message.success('感谢对我们的评价！');
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
