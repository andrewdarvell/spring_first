import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {Product} from '../../../model/user-shop';
import {environment} from '../../../../environments/environment';
import {Select} from "@ngxs/store";
import {UserState} from "../../../store/states/user.state";
import {Observable} from "rxjs";

@Component({
  selector: "app-all-products",
  templateUrl: "products.component.html",
  styleUrls: ["products.component.scss"]
})
export class ProductsAllComponent {

  @Select(UserState.hasAdminRole) hasAdminRole$: Observable<boolean> | undefined;

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
