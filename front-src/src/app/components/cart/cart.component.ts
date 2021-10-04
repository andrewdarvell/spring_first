import {Component, OnInit} from "@angular/core";
import {Select, Store} from "@ngxs/store";
import {Observable} from "rxjs";
import {CartItem, CartState} from "../../store/states/cart.state";
import {UserShopService} from "../../service/user-shop.service";

@Component({
    selector: "app-cart",
    templateUrl: "cart.component.html",
    styleUrls: ["cart.component.scss"]
})
export class CartComponent implements OnInit {

    @Select(CartState.cartItems) cartItems$!: Observable<CartItem[]>;

    constructor(private store: Store,
                private userShopService: UserShopService) {

    }

    ngOnInit(): void {
        this.cartItems$.subscribe(ci => {
            const arr = ci.map(i => i.productId);
            this.userShopService.getActualCosts(arr).subscribe(resp =>
                console.log(resp)
            )
        });
    }

}
