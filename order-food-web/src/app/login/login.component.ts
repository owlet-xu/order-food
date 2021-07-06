import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { LocalStorageService } from 'app/core/services/local-storage.service';
import { SessionStorageService } from 'app/core/services/session-storage.service';
import { LoginDataService } from 'app/core/services/login-data.service';
import { Router } from '@angular/router';
import { MyToolsService } from 'app/core/services/my-tools.service';
import { NzMessageService } from 'ng-zorro-antd';
import { TranslateService } from '@ngx-translate/core';
import { StartUpService } from 'app/core/services/startup.service';

@Component({
  selector: 'od-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginName: string;
  loginPassWord: string;

  constructor(
    private formBuilder: FormBuilder,
     private storage: LocalStorageService,
      private loginDataService: LoginDataService,
  private storageSession: SessionStorageService,
  private router: Router,
  private tools: MyToolsService,
  private message: NzMessageService,
  private translate: TranslateService,
  private startService: StartUpService
) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
    this.loginName = this.storage.get(this.storage.loginName);
    this.loginPassWord = this.storage.get(this.storage.loginPassWord);
  }

  submitForm(): void {
    for (const field in this.loginForm.controls) {
      if (field) {
        this.loginForm.controls[field].markAsDirty();
        this.loginForm.controls[field].updateValueAndValidity();
      }
    }

    if (this.loginForm.valid) {
      const { username, password } = this.loginForm.getRawValue();
      this.loginDataService.login(username, password, (data) => {
        if (data.success) {
          this.remeberNamePassWord(username, password);
          this.storageSession.setObject(this.storageSession.userData, data.data[0]);
          this.tools.startGetMessageThread();
          this.router.navigate(['home']);
        } else {
          this.message.warning(data.msg);
        }
      });
    }
  }
  remeberNamePassWord(username, password) {
    if (this.loginForm.controls.remember) {
      this.storage.set(this.storage.loginName, username);
      this.storage.set(this.storage.loginPassWord, password);
    } else {
      this.storage.remove(this.storage.loginName);
      this.storage.remove(this.storage.loginPassWord);
    }
  }
  changeLanguge () {
    const currentLang = this.translate.currentLang;
    const lang = currentLang === 'zh-CN' ? 'en-US' : 'zh-CN';
    this.startService.changeLan (lang);
  }
}
