import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CatalogComponent} from "./components/catalog/catalog.component";
import {CategoryEditorComponent} from './components/admin/category/category-editor.component';
import {AuthComponent} from './components/users/auth/auth.component';
import {CartComponent} from "./components/cart/cart.component";
import {OrdersComponent} from './components/orders/orders.component';
import {ProductTypesEditorComponent} from './components/admin/product-types-editor/product-types-editor.component';
import {FullProductEditorComponent} from './components/admin/full-product/full-product-editor.component';

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
                    {path: "product/:productId", component: FullProductEditorComponent},
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
