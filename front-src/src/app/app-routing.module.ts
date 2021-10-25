import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CatalogComponent} from "./components/catalog/catalog.component";
import {CategoryEditorComponent} from './components/admin/category/category-editor.component';
import {AuthComponent} from './components/users/auth/auth.component';
import {CartComponent} from "./components/cart/cart.component";
import {OrdersComponent} from './components/orders/orders.component';

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
                    {path: "category", component: CategoryEditorComponent}
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
