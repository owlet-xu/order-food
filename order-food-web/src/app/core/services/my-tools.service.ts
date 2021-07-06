import { Injectable } from '@angular/core';
import { LoginDataService } from 'app/core/services/login-data.service';
import { SessionStorageService } from 'app/core/services/session-storage.service';
import { Subject, Observable } from 'rxjs';

@Injectable()

export class MyToolsService {

  private subject = new Subject<any>(); // 消息发送者

  constructor(
    private loginDataService: LoginDataService,
    private storageSesson: SessionStorageService
  ) { }

  DateFormate(date: Date, fmt: string): string {
    if (null === date) {
      return '';
    }
    const o = {
      'M+': date.getMonth() + 1, // 月份
      'd+': date.getDate(), // 日
      'h+': date.getHours(), // 小时
      'm+': date.getMinutes(), // 分
      's+': date.getSeconds(), // 秒
      'q+': Math.floor((date.getMonth() + 3) / 3), // 季度
      'S': date.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (const k in o) {
      if (new RegExp('(' + k + ')').test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)));
      }
    }
    return fmt;
  }
  startGetMessageThread () {
    setInterval(() => {
      const userData = this.storageSesson.getObject(this.storageSesson.userData);
      this.loginDataService.getOrder(userData.id).subscribe(res => {
        const data = JSON.parse(JSON.stringify(res));
        this.subject.next (data);
      });
    }, 10000);
  }

  getMessage(): Observable<any> {
    return this.subject.asObservable();
  }
}
