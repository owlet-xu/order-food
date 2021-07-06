import {  Injectable } from '@angular/core';

@Injectable()

export class SessionStorageService {

  private mySession: any;
  public userData = 'userData';

  constructor () {
    if (!sessionStorage) {
      throw new Error('Current browser does not support sessionStorage');
    }
    this.mySession = sessionStorage;
  }

  public set(key: string, value: string, ms: number): void {
    this.mySession.setItem(key, value);
  }

  public get (key: string): string {
    return this.mySession.getItem(key);
  }

  public setObject (key: string, value: any): void {
    this.mySession[key] = JSON.stringify(value);
  }

  public getObject (key: string): any {
    return JSON.parse(this.mySession[key] || '{}');
  }

  public remove(key: string): void {
    this.mySession.removeItem(key);
  }

  public isLogin(): boolean {
    const aa = this.getObject(this.userData);
    if (aa.id) {
      return true;
    } else {
      return false;
    }
  }
}
