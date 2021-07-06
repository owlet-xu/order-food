import { NgModule, Optional, SkipSelf, APP_INITIALIZER } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { OwletConfigModule, ConfigLoader, ConfigHttpLoader } from '@gsafety/owlet-config';
import { NgZorroAntdModule } from 'ng-zorro-antd';

import { LoggerFactory } from './services/logger-factory';
import { GlobalErrorHandler } from './services/global-error-handler';
import { StartUpService } from './services/startup.service';
import { PreloadStrategy } from './services/preload-strategy';
import { MenuService } from './services/menu.service';

import { ShopDataService } from './services/shop-data.service';
import { LoginDataService } from './services/login-data.service';
import { CustomDataService } from './services/custom-data.service';
import { LocalStorageService } from 'app/core/services/local-storage.service';
import { SessionStorageService } from 'app/core/services/session-storage.service';
import { MyToolsService } from 'app/core/services/my-tools.service';

export function TranslateHttpLoaderFactory(httpClient: HttpClient) {
  return new TranslateHttpLoader(httpClient, './i18n/', '.json');
}

export function ConfigHttpLoaderFactory(httpClient: HttpClient) {
  return new ConfigHttpLoader(httpClient, './assets/config.json');
}

export function StartUpServiceFactory(startUpService: StartUpService) {
  return () => startUpService.load();
}

@NgModule({
  imports: [
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: TranslateHttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    OwletConfigModule.forRoot({
      loader: {
        provide: ConfigLoader,
        useFactory: ConfigHttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    NgZorroAntdModule
  ],
  exports: [
    TranslateModule,
    NgZorroAntdModule
  ],
  providers: [
    LoggerFactory,
    GlobalErrorHandler,
    StartUpService,
    PreloadStrategy,
    MenuService,
    {
      provide: APP_INITIALIZER,
      useFactory: StartUpServiceFactory,
      deps: [StartUpService],
      multi: true
    },
    ShopDataService,
    LoginDataService,
    CustomDataService,
    LocalStorageService,
    SessionStorageService,
    MyToolsService,
    TranslateService
  ]
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule is already loaded, Import it in the AppModule only');
    }
  }
}
