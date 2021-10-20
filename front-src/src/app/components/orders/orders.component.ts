import {Component} from '@angular/core';
import {FilterOrderRequest,  OrderListResponse} from '../../model/user-shop';
import {UserShopService} from '../../service/user-shop.service';
import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: "app-orders",
  templateUrl: "orders.component.html",
  styleUrls: ["orders.component.scss"]
})
export class OrdersComponent {

  public orderListResponse = {pageable:{pageSize:5}} as OrderListResponse;
  public filterRequest = {} as FilterOrderRequest;

  constructor(private shopService: UserShopService) {
  }

  ngOnInit(): void {
    this.getOrders();
  }

  getOrders(){
    this.shopService.getOrdersByCurrUser(this.filterRequest).subscribe(
      resp => {this.orderListResponse = resp; console.log(resp)}
    )
  }

  pageEventEmit(event: PageEvent){
    this.filterRequest.currPage = event.pageIndex;
    this.filterRequest.pageSize = event.pageSize;
    this.getOrders();
    console.log(event);
  }

}
