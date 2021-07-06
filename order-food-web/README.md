# OwletSeed

order-food-web是基于angular 6实现的前端种子工程

## 环境准备

1. 源码管理: Git
2. 开发IDE: VSCode
3. 包管理: nodejs@8.11.2  npm@5.6.0
4. 浏览器: Chrome

## 快速指南

1. 使用Git clone，将代码clone到本地

2. 执行`npm install`安装依赖

    > 安装依赖前，执行以下命令设置npm下载地址  
    > `npm config set registry=http://172.18.24.36:7001/`  
    > `npm config set SASS_BINARY_SITE=http://172.18.24.51:8081/nexus/content/sites/gs-assets/node/sass/`

3. 执行`npm start`开启本地开发服务器，并自动打开浏览器访问`http://localhost:4200`

    > `ts`, `scss`, `html`修改后自动编译。通知浏览器刷新

4. 执行`npm run test`运行单元测试

5. 执行`npm run lint`运行代码检查

## 业务

订餐登录页面：http://localhost:8020/#/login

## 用户类型判断

```
    if (!this.storageSession.isLogin()) {
      this.router.navigate(['login']);
    } else if (this.storageSession.getObject(this.storageSession.userData).type === '1') {
      this.router.navigate(['home/shop/my-shop']);
    } else {
      this.router.navigate(['home/custom/order-food']);
    }
```

