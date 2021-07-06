import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDataService } from 'app/core/services/login-data.service';
import { LocalStorageService } from 'app/core/services/local-storage.service';
import { NzModalService } from 'ng-zorro-antd';

import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from '@angular/forms';

@Component({
  selector: 'od-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  userType: any = '0';

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private loginService: LoginDataService,
    private storage: LocalStorageService,
    private model: NzModalService
  ) {

  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: [undefined, [Validators.required, Validators.minLength(2), Validators.maxLength(10)]],
      password: [undefined, [Validators.required, Validators.minLength(6), Validators.maxLength(20)]],
      password2: [undefined, [Validators.required, this.validatePassw]],
      phone: [undefined, [Validators.required, this.validatePhone]],
      email: [undefined, [Validators.required, Validators.email]],
      address: [undefined, [Validators.required]],
      type: [undefined, undefined]
    });
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
  // 提交
  submitForm(): void {
    for (const field in this.registerForm.controls) {
      if (field) {
        this.registerForm.controls[field].markAsDirty();
        this.registerForm.controls[field].updateValueAndValidity();
      }
    }
    if (this.registerForm.valid) {
      const { username, password, password2, phone, email, address } = this.registerForm.getRawValue();
      this.loginService.register(username, password, email, phone, address, this.userType, (data) => {
        if (data.success) {
          // 保存用户数据
          this.storage.set(this.storage.loginName, phone);
          this.storage.set(this.storage.loginPassWord, password);
          this.model.info({
            nzTitle: '提示',
            nzContent: '注册成功',
            nzOnOk: () => {
              this.router.navigate(['login']);
            }
          });
        } else {
          this.model.info({
            nzTitle: '提示',
            nzContent: data.msg,
            nzOnOk: () => { }
          });
        }
      });
    }
  }
}
