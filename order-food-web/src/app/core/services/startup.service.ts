import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { ConfigService } from '@gsafety/owlet-config';
import { concatMap } from 'rxjs/operators';
import { registerLocaleData } from '@angular/common';
import ng_zh from '@angular/common/locales/zh';
import ng_en_US from '@angular/common/locales/en-US-POSIX';
import ng_pt_PT from '@angular/common/locales/pt-PT';
import { zh_CN as nz_zh_CN, en_US as nz_en_US, pt_PT as nz_pt_PT, NzI18nService } from 'ng-zorro-antd';

import { LoggerFactory, Logger } from './logger-factory';

const nzLocales = {
  'zh-CN': nz_zh_CN,
  'en-US': nz_en_US,
  'pt-PT': nz_pt_PT
};

const ngLocales = {
  'zh-CN': ng_zh,
  'en-US': ng_en_US,
  'pt-PT': ng_pt_PT
};

@Injectable()
export class StartUpService {

  private readonly SUPPORT_LANGS = ['zh-CN', 'en-US', 'pt-PT'];
  private readonly DEFAULT_LANG = 'zh-CN';
  private logger: Logger;

  constructor(
    private translate: TranslateService,
    private nzI18nService: NzI18nService,
    private configService: ConfigService,
    private loggerFactory: LoggerFactory
  ) {}

  public load(): Promise<any> {
    return this.configService.load().pipe(
      concatMap(c => {
        this.loggerFactory.setConfig();
        this.logger = this.loggerFactory.getLogger('StartUpService');

        let lang = this.configService.instant('lang');
        if (!this.isSupportLang(lang)) {
          lang = this.DEFAULT_LANG;
        }
        this.logger.info(`current language is ${lang}`);
        registerLocaleData(ngLocales[lang]);
        this.nzI18nService.setLocale(nzLocales[lang]);

        return this.translate.use(lang);
      })
    ).toPromise();
  }

  private isSupportLang(lang: string): boolean {
    return this.SUPPORT_LANGS.indexOf(lang) >= 0;
  }

  public changeLan (lang: string) {
    registerLocaleData(ngLocales[lang]);
    this.nzI18nService.setLocale(nzLocales[lang]);
    this.translate.use(lang);
    this.logger.info(`current language is ${lang}`);
  }
}
