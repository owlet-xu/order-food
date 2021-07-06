import { Provider, Injectable } from '@angular/core';

@Injectable()
export class LocalStorageService {

  private localStorage: any;
  public loginName = 'loginName';
  public loginPassWord = 'loginPassWord';
  public baseUrl = 'baseUrl';
  public baseImgUrl = 'baseImgUrl';
  public baseImgUploadUrl = 'baseImgUploadUrl';
  constructor() {
    if (!localStorage) {
      throw new Error('Current browser does not support Local Storage');
    }
    this.localStorage = localStorage;
    this.set(this.baseUrl, 'http://localhost:32113');
    this.set(this.baseImgUrl, 'http://172.19.14.22:10000/');
    this.set(this.baseImgUploadUrl, 'http://localhost:32113/api/v1/upload');
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
