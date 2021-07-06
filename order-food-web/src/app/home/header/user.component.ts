import { Component, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { SessionStorageService } from 'app/core/services/session-storage.service';
import { LocalStorageService } from 'app/core/services/local-storage.service';
import { TranslateService } from '@ngx-translate/core';
import { StartUpService } from 'app/core/services/startup.service';

@Component({
    selector: 'od-header-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.scss']
})

export class UserComponent {

    userData: any;
    baseImgUrl: string;

    constructor(
      private router: Router,
      private storageSession: SessionStorageService,
      private translate: TranslateService,
      private startServer: StartUpService,
      private storage: LocalStorageService
    ) {
      this.userData = this.storageSession.getObject(this.storageSession.userData);
      this.baseImgUrl = this.storage.get(this.storage.baseImgUrl);
    }

    loginOut(): void {
      this.storageSession.setObject(this.storageSession.userData, {});
      this.router.navigate(['login']);
    }

    toMyInfo(): void {
      if (this.userData.type === '0') {
        this.router.navigate(['home/custom/my-info']);
      } else if (this.userData.type === '1') {
        this.router.navigate(['home/shop/shop-info']);
      }
    }
    changeLanguge () {
      const currentLang = this.translate.currentLang;
      const lang = currentLang === 'zh-CN' ? 'en-US' : 'zh-CN';
      this.startServer.changeLan(lang);
    }
}
