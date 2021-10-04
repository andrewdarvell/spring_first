import {Product} from "../../model/user-shop";

export class AddProductToCart {
    static readonly type = '[CartItems] add';
    constructor(public payload: Product) {}
}
