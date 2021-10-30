import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CatalogComponent} from "./components/catalog/catalog.component";
import {CategoryEditorComponent} from './components/admin/category/category-editor.component';
import {AuthComponent} from './components/users/auth/auth.component';
import {CartComponent} from "./components/cart/cart.component";
import {OrdersComponent} from './components/orders/orders.component';
import {ProductTypesEditorComponent} from './components/admin/product-types-editor/product-types-editor.component';
import {ProductValuesEditorComponent} from './components/admin/full-product/product-values-editor.component';

const routes: Routes = [
    {path: '', redirectTo: 'app/catalog', pathMatch: 'full'},
    {
        path: "app", children: [
            {path: "auth", component: AuthComponent},
            {path: "catalog", component: CatalogComponent},
            {path: "cart", component: CartComponent},
            {path: "orders", component: OrdersComponent},
            {
                path: "admin", children: [
                    {path: "category", component: CategoryEditorComponent},
                    {path: "types", component: ProductTypesEditorComponent},
                    {path: "product_values/:productId", component: ProductValuesEditorComponent},
                ]
            },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
