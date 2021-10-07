import {Injectable} from "@angular/core";
import {Product} from "../../model/user-shop";
import {Action, Selector, State, StateContext} from "@ngxs/store";
import {AddProductToCart, UpdateCost} from "../actions/cart.actions";
import {append, patch, updateItem} from "@ngxs/store/operators";

export interface CartItem {
    productId: number;
    title: string;
    cost: number;
    count: number;
}

export class CartItemFromProduct implements CartItem {
    productId: number;
    cost: number;
    count: number;
    title: string;

    constructor(product: Product, count: number) {
        this.productId = product.id ?? -1;
        this.title = product.title;
        this.cost = product.cost;
        this.count = count;
    }
}

export class CartItemFromItemUpdateCost implements CartItem {
    productId: number;
    cost: number;
    count: number;
    title: string;

    constructor(cartItem: CartItem, cost: number) {
        this.productId = cartItem.productId;
        this.title = cartItem.title;
        this.count = cartItem.count;
        this.cost = cost;
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

    @Action(UpdateCost)
    updateCostInCart(ctx: StateContext<CartStateModel>, {payload}: UpdateCost) {
        const state = ctx.getState();
        const costs = payload.costs;
        state.items.forEach(item => {
            const itemCost = costs[item.productId];
            if (itemCost && itemCost !== item.cost) {
                const updatedItem = new CartItemFromItemUpdateCost(item, itemCost);
                ctx.setState(
                    patch({
                        items: updateItem<CartItem>(i => i?.productId === item.productId, updatedItem)
                    })
                );
            }
        });
    }

    @Action(AddProductToCart)
    addProductToCart(ctx: StateContext<CartStateModel>, {payload}: AddProductToCart) {
        const state = ctx.getState();
        let item = state.items.find(i => i.productId === payload.id);
        if (item) {
            const updatedItem = new CartItemFromProduct(payload, item.count + 1);
            if (updatedItem.productId !== -1) {
                ctx.setState(
                    patch({
                        items: updateItem<CartItem>(i => i?.productId === payload.id, updatedItem)
                    })
                );
            }
        } else {
            ctx.setState(
                patch({
                    items: append([new CartItemFromProduct(payload, 1)])
                })
            );
        }
    }
}
