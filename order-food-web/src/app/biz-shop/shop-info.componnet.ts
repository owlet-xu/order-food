import { Component, OnInit } from '@angular/core';
import { SessionStorageService } from 'app/core/services/session-storage.service';
import { LocalStorageService } from 'app/core/services/local-storage.service';
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { NzMessageService, UploadFile } from 'ng-zorro-antd';
import { ShopDataService } from 'app/core/services/shop-data.service';
import { UserInfo } from 'app/models/user-info';
import { MyToolsService } from 'app/core/services/my-tools.service';

@Component({
  selector: 'od-shop-info',
  templateUrl: './shop-info.componnet.html',
  styleUrls: ['./shop-info.componnet.scss']
})

export class ShopInfoComponent implements OnInit {

  userData: UserInfo;
  isShowEdit = false;
  registerForm: FormGroup;
  userSex = '0';
  fileList = [];
  previewImage = '';
  previewVisible = false;
  imgUploadUrl: string;
  baseImgUrl: string;
  confirmPass: string;
  uploading = false; // 是否有正在上传图片

  constructor(
    private storageSession: SessionStorageService,
    private router: Router,
    private formBuilder: FormBuilder,
    private shopData: ShopDataService,
    private message: NzMessageService,
    private storage: LocalStorageService,
    private myTools: MyToolsService
  ) {
    if (!this.storageSession.isLogin()) {
      this.router.navigate(['login']);
    }

    this.userData = this.storageSession.getObject(this.storageSession.userData);
    this.imgUploadUrl = this.storage.get(this.storage.baseImgUploadUrl);
    console.log('=-------' + this.imgUploadUrl);
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: [null, [Validators.required, Validators.minLength(2), Validators.maxLength(10)]],
      password: [null, [Validators.required, Validators.minLength(6), Validators.maxLength(20)]],
      password2: [null, [Validators.required, this.validatePassw]],
      phone: [null, [Validators.required, this.validatePhone]],
      email: [null, [Validators.required, Validators.email]],
      address: [null, [Validators.required]],
      interest: [null, null],
      age: [null, null],
      sex: [null, null]
    });
    this.baseImgUrl = this.storage.get(this.storage.baseImgUrl);
    this.confirmPass = this.userData.passWord;
  }
  // 图片预览
  handlePreview = (file: UploadFile) => {
    this.previewImage = file.url || file.thumbUrl;
    this.previewVisible = true;
  }

  // 密码验证
  validatePassw = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {// 不存在
      return { required: true };
    } else if (this.registerForm.controls.password.value !== this.registerForm.controls.password2.value) {// 密码不一致
      return { confirm: true, error: true };
    } else if (this.registerForm.controls.password2.value.length < 6) {
      return { lenShort: true, error: true };
    }
  }
  // 手机号验证
  validatePhone = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (/^(13|15|18|17)\d{9}$/i.test(control.value) !== true) {
      return { phoneFormateError: true, error: true };
    }
  }

  showOrCloseModel(isShow: boolean) {
    this.isShowEdit = isShow;
  }

  save() {
    this.userData.sex = this.userSex;
    this.shopData.editUser(this.userData).subscribe(res => {
      const data = JSON.parse(JSON.stringify(res));
      if (data.success) {
        this.message.success('修改成功');
        this.storageSession.setObject(this.storageSession.userData, this.userData);
        this.isShowEdit = false;
      }
    });
  }
  getSexName(key) {
    switch (key) {
      case '0':
        return '男';
      case '1':
        return '女';
    }
  }
  uploadChange(event) {
    if (event.type === 'success') {
      this.userData.headImg = event.fileList[0].response.fileName;
    }
  }

  // nzBeforeUpload 返回 false 后，手动上传文件。
  beforeUpload = (file: any): boolean => {
    this.uploading = true;
    this.fileList.push(file);
    return false;
  }

  // 上传图片
  upload() {
    if (!this.validForm()) {
      return;
    }
    if (this.uploading) {
      this.myTools.upload(this.fileList, (data: any) => {
        if (!Array.isArray(data)) {
          this.message.error('上传图片失败');
          return;
        }
        this.userData.headImg = data[0].fileId;
        this.save();
      });
    } else {
      this.save();
    }
  }
  // 验证
  validForm(): boolean {
    if (this.uploading && this.fileList.length < 1) {
      this.message.warning('至少添加2张图片');
      return false;
    }
    for (const field in this.registerForm.controls) {
      if (field) {
        this.registerForm.controls[field].markAsDirty();
        this.registerForm.controls[field].updateValueAndValidity();
      }
    }
    return this.registerForm.valid;
  }
}
