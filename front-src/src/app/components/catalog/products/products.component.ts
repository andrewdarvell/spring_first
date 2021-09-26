import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {Product} from '../../../model/user-shop';
import {environment} from '../../../../environments/environment';

@Component({
  selector: "app-all-products",
  templateUrl: "products.component.html",
  styleUrls: ["products.component.scss"]
})
export class ProductsAllComponent {

  @Input()
  public products: Product[] = [];

  @Output()
  onEditAction: EventEmitter<Product> = new EventEmitter();

  getProductImageLink(url: string): string {
    return `${environment.apiRootEndpoint}${url}`
  }

  onEdit(id: number | undefined) {
    if (id) {
      const product = this.products.find(p => p.id === id);
      this.onEditAction.emit(product);
    }
  }

}
