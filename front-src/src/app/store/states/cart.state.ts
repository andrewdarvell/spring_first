import {Injectable} from "@angular/core";
import {Product} from "../../model/user-shop";
import {Action, Selector, State, StateContext} from "@ngxs/store";
import {AddProductToCart} from "../actions/cart.actions";
import {append, patch, updateItem} from "@ngxs/store/operators";

export class CartItem {
    productId: number | undefined;
    title: string;
    cost: number;
    count: number;

    constructor(product: Product, count: number) {
        this.productId = product.id;
        this.title = product.title;
        this.cost = product.cost;
        this.count = count;
    }
}

export class CartStateModel {
    items: CartItem[] = [];
}

@State<CartStateModel>({
    name: 'cart_items',
    defaults: {
        items: []
    }
})
@Injectable()
export class CartState {

    @Selector()
    static cartItems(state: CartStateModel): CartItem[] {
        return state.items;
    }

    @Action(AddProductToCart)
    addProductToCart(ctx: StateContext<CartStateModel>, {payload}: AddProductToCart) {
        const state = ctx.getState();
        let item = state.items.find(i => i.productId === payload.id);
        if (item) {
            ctx.setState(
                patch({
                    items: updateItem<CartItem>(i => i?.productId === payload.id, new CartItem(payload, item.count + 1))
                })
            );
        } else {
            ctx.setState(
                patch({
                    items: append([new CartItem(payload, 1)])
                })
            );
        }
    }
}
