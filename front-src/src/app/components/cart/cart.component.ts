import {Component, OnInit} from "@angular/core";
import {Select, Store} from "@ngxs/store";
import {Observable} from "rxjs";
import {CartItem, CartState} from "../../store/states/cart.state";
import {UserShopService} from "../../service/user-shop.service";
import {DeleteProduct, UpdateCost} from "../../store/actions/cart.actions";
import {FormBuilder, Validators} from '@angular/forms';
import {UserState} from '../../store/states/user.state';
import {OrderItem} from '../../model/user-shop';
import {StateReset} from 'ngxs-reset-plugin';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: "app-cart",
  templateUrl: "cart.component.html",
  styleUrls: ["cart.component.scss"]
})
export class CartComponent implements OnInit {

  @Select(CartState.cartItems) cartItems$!: Observable<CartItem[]>;

  @Select(CartState.cartCost) cartCost$!: Observable<number>;

  @Select(UserState.hasUserRole) hasUserRole$!: Observable<boolean> | undefined;

  columnsToDisplay = ['title', 'cost', 'count', 'actions'];

  orderForm = this.fb.group({
    deliveryAddress: ['', Validators.required],
    comment: [''],
  })


  constructor(private store: Store,
              private userShopService: UserShopService,
              private fb: FormBuilder,
              public snackBar: MatSnackBar) {

  }

  ngOnInit(): void {
    this.cartItems$.subscribe(ci => {
      const arr = ci.map(i => i.productId);
      this.userShopService.getActualCosts(arr).subscribe(resp =>
        this.store.dispatch(new UpdateCost(resp))
      )
    });
  }

  delete(id: number) {
    console.log(id);
    this.store.dispatch(new DeleteProduct(id));
  }

  doOrder() {
    if (this.orderForm.valid) {
      const items: OrderItem[] = [];

      const sub = this.cartItems$.subscribe(i => i.forEach(v => items.push({productId: v.productId, count: v.count})));
      sub.unsubscribe();

      let order = {
        deliveryAddress: this.orderForm.get('deliveryAddress')?.value,
        comment: this.orderForm.get('comment')?.value,
        shopOrderItems: items
      };

      this.userShopService.addOrder(order).subscribe(resp => {
        this.store.dispatch(
          new StateReset(CartState)
        );
      }, error => {
        if (error.status === 403) {
          this.snackBar.open('Необходимо авторизоваться', 'Ок', {duration: 3000});
        }
      });
    }
  }

}
