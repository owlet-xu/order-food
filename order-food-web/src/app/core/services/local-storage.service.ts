import { Provider, Injectable } from '@angular/core';

@Injectable()
export class LocalStorageService {

  private localStorage: any;
  public loginName = 'loginName';
  public loginPassWord = 'loginPassWord';
  public baseUrl = 'http://121.196.145.103:32102';
  public baseImgUrl = 'http://121.196.145.103:31111/orderfood/';
  public baseImgUploadUrl = 'http://121.196.145.103:32101';
  constructor() {
    if (!localStorage) {
      throw new Error('Current browser does not support Local Storage');
    }
    this.localStorage = localStorage;
    this.set(this.baseUrl, 'http://121.196.145.103:32102');
    this.set(this.baseImgUrl, 'http://121.196.145.103:31111/orderfood/');
    this.set(this.baseImgUploadUrl, 'http://121.196.145.103:32101');
  }

  public set(key: string, value: string): void {
    this.localStorage[key] = value;
  }

  public get (key: string): string {
    return this.localStorage[key];
  }

  public setObject (key: string, value: any): void {
    this.localStorage[key] = JSON.stringify(value);
  }

  public getObject (key: string): any {
    return JSON.parse(this.localStorage[key] || '{}');
  }

  public remove(key: string): void {
    this.localStorage.removeItem(key);
  }
}
