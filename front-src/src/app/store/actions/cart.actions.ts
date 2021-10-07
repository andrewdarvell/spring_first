import {CostsResponse, Product} from "../../model/user-shop";

export class AddProductToCart {
    static readonly type = '[CartItems] add';
    constructor(public payload: Product) {}
}

export class UpdateCost {
    static readonly type = '[CartItems] update_cost';
    constructor(public payload: CostsResponse) {}
}
