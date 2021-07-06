import { Component, Input } from '@angular/core';
import { LocalStorageService } from 'app/core/services/local-storage.service';

@Component({
  selector: 'od-my-custom-detail',
  template: `
  <div class='info'>
    <div style='display: flex;'>
     <div style='flex: 0 0 70px;'>
      <img class="headImg" [src]="baseImgUrl + userData.headImg" />
     </div>
     <div style='flex: 1 1 auto;'>
      <div style="font-size: 20px;font-border:border;">{{userData?.name}}</div>
      <div>{{userData?.age}}岁 {{getSexName(userData?.sex)}}</div>
     </div>
    </div>
    <div style="margin-top: 10px;">
     <p>手机：{{userData?.phone}}</p>
     <p>邮箱：{{userData?.email}}</p>
     <p>类型：{{getUserType(userData?.type)}}</p>
     <p>住址：{{userData?.address}}</p>
     <p>兴趣：{{userData?.interest}}</p>
     </div>
    <div>
</div>
  `,
  styleUrls: ['./my-custom-detail.component.scss']
})
export class MycustomDetailComponent {
  @Input() userData: any;
  baseImgUrl: string;

  constructor(private storage: LocalStorageService) {
    this.baseImgUrl = this.storage.get(this.storage.baseImgUrl);
  }

  getSexName (val) {
    if (val === '1') {
      return '女';
    } else {
      return '男';
    }
  }
  getUserType (val) {
    if (val === '0') {
      return '客户';
    } else if (val === '1') {
      return '商家';
    } else {
      return '未知';
    }
  }
}
