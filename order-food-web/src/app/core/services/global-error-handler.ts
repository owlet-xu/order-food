import { ErrorHandler, Injectable } from '@angular/core';

import { LoggerFactory, Logger } from './logger-factory';

/**
 * 全局异常捕获
 *
 * @export
 * @class GlobalErrorHandler
 * @extends {ErrorHandler}
 */
@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

  private logger: Logger;

  constructor(loggerFactory: LoggerFactory) {
    this.logger = loggerFactory.getLogger('GlobalErrorHandler');
  }

  /**
   * 捕获异常
   */
  handleError(error: any): void {
    this.logger.fatal(error.message);
    this.logger.fatal(error.stack);
  }
}
