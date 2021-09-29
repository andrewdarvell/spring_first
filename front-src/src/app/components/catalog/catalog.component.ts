import {Component, EventEmitter, OnInit} from "@angular/core";
import {UserShopService} from '../../service/user-shop.service';
import {FilterProductRequest, Product, ProductListResponse} from '../../model/user-shop';
import {FilteringProductData} from './filters/filters.component';
import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: "app-catalog",
  templateUrl: "catalog.component.html",
  styleUrls: ["catalog.component.scss"]
})
export class CatalogComponent implements OnInit {

  public productListResponse = {pageable:{pageSize:5}} as ProductListResponse;
  public filterRequest = {} as FilterProductRequest;
  public productToEdit = {} as Product;

  constructor(private shopService: UserShopService) {
  }

  ngOnInit(): void {
    this.getProducts();
  }

  doFiltering(filterData: FilteringProductData) {
    this.filterRequest.maxCost = filterData.maxCost;
    this.filterRequest.minCost = filterData.minCost;
    this.filterRequest.title = filterData.title;
    this.filterRequest.categoryId = filterData.categoryId;
    this.filterRequest.currPage = 0;
    this.getProducts();
  }

  onEditProduct(product: Product){
    this.productToEdit = {} as Product;
    this.productToEdit = product;
    console.log(product);
  }

  getProducts(){
    this.shopService.getProducts(this.filterRequest).subscribe(
      resp => {this.productListResponse = resp; console.log(resp)}
    )
  }

  pageEventEmit(event: PageEvent){
    this.filterRequest.currPage = event.pageIndex;
    this.filterRequest.pageSize = event.pageSize;
    this.getProducts();
    console.log(event);
  }

  public productUpdated(product: Product) {
    this.getProducts();
  }

}
