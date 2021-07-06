import { Injectable } from '@angular/core';
import { JL } from 'jsnlog';

export interface Logger {
  /**
   * 追踪信息
   * @param {any} logObject -日志对象
   */
  trace(logObject: any): void;

  /**
   * 调试信息
   * @param {any} logObject -日志对象
   */
  debug(logObject: any): void;

  /**
   * 信息
   * @param {any} logObject -日志对象
   */
  info(logObject: any): void;

  /**
   * 警告
   * @param {any} logObject -日志对象
   */
  warn(logObject: any): void;

  /**
   * 错误
   * @param {any} logObject -日志对象
   */
  error(logObject: any): void;

  /**
   * 严重错误
   * @param {any} logObject -日志对象
   */
  fatal(logObject: any): void;
}

export interface LoggerOptions {
  url: string;
}

@Injectable()
export class LoggerFactory {
  setConfig(options?: LoggerOptions) {
    const appenders: JL.JSNLogAppender[] = [];

    const consoleAppender = JL.createConsoleAppender('consoleAppender');
    appenders.push(consoleAppender);

    JL().setOptions({
      appenders
    });
  }

  getLogger(categoryName: string = 'app'): Logger {
    return JL(categoryName);
  }
}
