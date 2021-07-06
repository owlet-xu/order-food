import { Component, OnInit } from '@angular/core';
import { ShopDataService } from 'app/core/services/shop-data.service';


@Component({
  selector: 'od-my-shop',
  templateUrl: './my-shop.component.html',
  styleUrls: ['./my-shop.component.scss']
})

export class MyShopComponent implements OnInit {

  options: any;
  options2: any;
  totalData = [{
    title: '今日订单数量',
    content: 'xx单'
  }, {
    title: '今日销售金额',
    content: 'xx元'
  }, {
    title: '本月订单数量',
    content: 'xx单'
  }, {
    title: '本月销售金额',
    content: 'xx元'
  }];

  constructor (private shopData: ShopDataService) { }

  ngOnInit () {
    console.log('--------------------');
    this.getTodayCount();
    this.getMonthCount();
    this.getTodayMoney();
    this.getMonthMoney();
    this.getTodaySum();
    this.autoFreshData();
  }

  // 0：今天，1：本月
  getTodayCount () {
    this.shopData.getCount('0').subscribe(res => {
      const data = JSON.parse(JSON.stringify(res));
      if (data.success) {
        console.log(data);
        this.totalData[0].content = data.data + '单';
      }
    });
  }
    // 0：今天，1：本月
    getMonthCount () {
      this.shopData.getCount('1').subscribe(res => {
        const data = JSON.parse(JSON.stringify(res));
        if (data.success) {
          console.log(data);
          this.totalData[2].content = data.data + '单';
        }
      });
    }

  // 0：今天，1：本月
  getTodayMoney () {
    this.shopData.getMoney('0').subscribe(res => {
      const data = JSON.parse(JSON.stringify(res));
      if (data.success) {
        console.log(data);
        this.totalData[1].content = data.data + '元';
      }
    });
  }
    // 0：今天，1：本月
    getMonthMoney () {
    this.shopData.getMoney('1').subscribe(res => {
      const data = JSON.parse(JSON.stringify(res));
      if (data.success) {
        console.log(data);
        this.totalData[3].content = data.data + '元';
      }
    });
  }

  // 获取今日订单和数目统计
  getTodaySum () {
    this.shopData.getTodaySum().subscribe(res => {
      const data = JSON.parse(JSON.stringify(res));
      if (data.success) {
        const pieData1 = [
          {value: data.data.countNumber['0'], name: '炒菜'},
          {value: data.data.countNumber['1'], name: '盖浇饭'},
          {value: data.data.countNumber['2'], name: '面条'},
          {value: data.data.countNumber['3'], name: '饮料'}
        ];
        const pieData2 = [
          {value: data.data.countMoneys['0'], name: '炒菜'},
          {value: data.data.countMoneys['1'], name: '盖浇饭'},
          {value: data.data.countMoneys['2'], name: '面条'},
          {value: data.data.countMoneys['3'], name: '饮料'}
        ];
        this.initTodayCountPie(pieData1);
        this.initTodayMoneyPie(pieData2);
      }
    });
  }
  initTodayCountPie (data: any) {
    this.options = {
      title: {
        text: '今日订单数量',
        subtext: '单位：单',
        x: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a}<br/>{b}:{c}单({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['炒菜', '盖浇饭', '面条', '饮料']
      },
      series: [
        {
          name: '订单数目',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: data,
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0,0,0,0.5)'
            }
          }
        }
      ]
    };
  }
  initTodayMoneyPie (data: any) {
    this.options2 = {
      title: {
        text: '今日订单金额',
        subtext: '单位：元',
        x: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a}<br/>{b}:{c}元({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['炒菜', '盖浇饭', '面条', '饮料']
      },
      series: [
        {
          name: '订单金额',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: data,
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0,0,0,0.5)'
            }
          }
        }
      ]
    };
  }

  autoFreshData () {
    setInterval(() => {
      this.getTodayCount();
      this.getMonthCount();
      this.getTodayMoney();
      this.getMonthMoney();
      this.getTodaySum();
    }, 10000);
  }
}
