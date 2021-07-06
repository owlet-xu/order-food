import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { NzMessageService, UploadFile, NzModalService } from 'ng-zorro-antd';
import { ShopDataService } from 'app/core/services/shop-data.service';
import { SessionStorageService } from 'app/core/services/session-storage.service';
import { LocalStorageService } from 'app/core/services/local-storage.service';

@Component({
  selector: 'od-food-manager',
  templateUrl: './food-manager.component.html',
  styleUrls: ['./food-manager.component.scss']
})

export class FoodManagerComponent implements OnInit {
  // 搜索框
  validateForm: FormGroup;
  controlArray = [];
  data = [];
  dataLoading = false;
  // 添加菜品对话框
  isShowAdd = false;
  validateFormAdd: FormGroup;
  fileList = [];
  previewImage = '';
  previewVisible = false;
  foodTypeSelect = '0';
  isDiscountSelect = '0';

  baseImgUrl: string;
  baseImgUploadUrl: string;

  constructor(
    private fb: FormBuilder,
    private shopDataService: ShopDataService,
    private message: NzMessageService,
    private model: NzModalService,
    private storageSession: SessionStorageService,
    private storage: LocalStorageService
  ) { }

  ngOnInit() {
    this.baseImgUrl = this.storage.get(this.storage.baseImgUrl);
    this.baseImgUploadUrl  = this.storage.get(this.storage.baseImgUploadUrl);
    // 搜索表单初始化
    this.validateForm = this.fb.group({});
    this.controlArray.push({
      index: 1,
      show: true,
      title: '菜品名',
      placeholder: '请输入菜品名',
      type: 'text',
      domType: 'input'
    });
    this.validateForm.addControl(`field1`, new FormControl());
    this.controlArray.push({
      index: 2,
      show: true,
      title: '价格区间',
      placeholder: '请选择价格区间',
      type: 'text',
      domType: 'select_price'
    });
    this.validateForm.addControl(`field2`, new FormControl());
    this.controlArray.push({
      index: 3,
      show: true,
      title: '菜品类型',
      placeholder: '请选择菜品类型',
      type: 'text',
      domType: 'select_type'
    });
    this.validateForm.addControl(`field3`, new FormControl());
    this.controlArray.push({
      index: 4,
      show: true,
      title: '菜品状态',
      placeholder: '请选择菜品状态',
      type: 'text',
      domType: 'select_status'
    });
    this.validateForm.addControl(`field4`, new FormControl());
    // 添加表单初始化
    this.validateFormAdd = this.fb.group({
      foodName         : [ null, [ Validators.required, Validators.minLength(2), Validators.maxLength(10) ] ],
      price            : [ null, [ Validators.required, Validators.min(0), Validators.max(9999999) ] ],
      discountPrice    : [ null, null ],
      isDiscount       : [ null, null ],
      foodType         : [ null, null ],
      picture          : [ null, null ],
      remarks          : [ null, null ]
    });
    this.getList();
  }
    // 图片预览
    handlePreview = (file: UploadFile) => {
      this.previewImage = file.url || file.thumbUrl;
      this.previewVisible = true;
    }
  getList () {
    for (const field in this.validateForm.controls) {
      if (field) {
        this.validateForm.controls[field].markAsDirty();
        this.validateForm.controls[field].updateValueAndValidity();
      }
    }
    const { field1, field2, field3, field4 } = this.validateForm.getRawValue();
    let minPrice;
    let maxPrice;
    if (field2 === '1') {
      minPrice = 0;
      maxPrice = 10;
    } else if (field2 === '2') {
      minPrice = 10;
      maxPrice = 20;
    } else if (field2 === '3') {
      minPrice = 20;
      maxPrice = 0;
    } else {
      minPrice = 0;
      maxPrice = 0;
    }
    this.data = [];
    this.dataLoading = true;
    this.shopDataService.getFoodList(field1, minPrice, maxPrice, field3, field4, (data) => {
      console.log(data);
      if (data.success) {
        this.data = data.data.content;
      } else {
        this.message.success(data.msg);
      }
      this.dataLoading = false;
    });
  }
  resetForm(): void {
    this.validateForm.reset();
  }
  showAdd() {
    this.isShowAdd = true;
  }
  closeAdd() {
    this.isShowAdd = false;
  }
  add() {
    for (const field in this.validateFormAdd.controls) {
      if (field) {
        this.validateFormAdd.controls[field].markAsDirty();
        this.validateFormAdd.controls[field].updateValueAndValidity();
      }
    }
    if (this.fileList.length < 2) {
      alert('至少上传两张图片');
      return;
    }
    let pics: string[];
    pics = ['', '', '', ''];
    for (let i = 0; i < this.fileList.length; i++) {
      pics[i] = this.fileList[i].response.fileName;
    }
    if (this.validateFormAdd.valid) {
      const { foodName, price, discountPrice, isDiscount, foodType, remarks } = this.validateFormAdd.getRawValue();
      const userId = this.storageSession.getObject(this.storageSession.userData).id;
      this.shopDataService.addFood(foodName, price, discountPrice, isDiscount,
         foodType,  pics[0], pics[1],
         pics[2], pics[3], userId,
         remarks, (data) => {
        if (data.success) {
          this.closeAdd();
          this.message.success('添加成功');
          this.getList();
          this.validateFormAdd.reset();
        }
      });
    }
   }
   uploadChange = (event) => {
      // if (event.type === 'success') {
      //   this.uploadImgNames[this.fileList.length - 1] = event.fileList[0].response.fileName;
      // }
   }

   foodActive (id: string, status: string) {
    let msg: string;
    if (status === '1') {
      msg = '下架成功';
     } else {
      msg = '上架成功';
     }
     this.shopDataService.foodActive(id, status, (data) => {
        if (data.success) {
          this.getList();
          this.message.success(msg);
        }
     });
   }

   foodDelete (id: string, name: string) {
     this.model.confirm({
       nzTitle: '确认',
       nzContent: `是否删除${name}?`,
       nzOnOk: () => {
        this.shopDataService.foodDelete(id, (data) => {
          if (data.success) {
            this.getList();
            this.message.success('删除成功');
          }
       });
       }
     });
   }

   getFoodTypeName (type: string): any {
      switch (type) {
        case '0' :
        return '炒菜';
        case '1':
        return '盖浇饭';
        case '2':
        return '面条';
        case '3':
        return '饮料';
        default:
        return type;
      }
   }

   getStatusName (type: string): any {
    switch (type) {
      case '0' :
      return '上架';
      case '1':
      return '下架';
      default:
      return type;
    }
   }
}
