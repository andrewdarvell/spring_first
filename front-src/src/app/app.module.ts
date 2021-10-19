import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HeaderComponent} from "./components/header/header.component";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MyOwnCustomMaterialModule} from "./material.module";
import {CatalogComponent} from "./components/catalog/catalog.component";
import {ProductsAllComponent} from "./components/catalog/products/products.component";
import {FiltersComponent} from "./components/catalog/filters/filters.component";
import {ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {CategoryEditorComponent} from './components/admin/category/category-editor.component';
import {AuthComponent} from './components/users/auth/auth.component';
import {AuthenticationInterceptor} from './interceptors/auth_interceptor';
import {ProductEditorComponent} from './components/admin/product/product-editor.component';
import {NgxsModule} from "@ngxs/store";
import {environment} from "../environments/environment";
import {UserState} from "./store/states/user.state";
import {CartState} from "./store/states/cart.state";
import {CartComponent} from "./components/cart/cart.component";
import {NgxsStoragePluginModule} from "@ngxs/storage-plugin";
import {NgxsResetPluginModule} from 'ngxs-reset-plugin';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CatalogComponent,
    ProductsAllComponent,
    FiltersComponent,
    CategoryEditorComponent,
    AuthComponent,
    ProductEditorComponent,
    CartComponent
  ],
  imports: [
    NgxsModule.forRoot([UserState, CartState], {
      developmentMode: !environment.production
    }),
    NgxsResetPluginModule.forRoot(),
    NgxsStoragePluginModule.forRoot({key: 'cart_items'}),
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MyOwnCustomMaterialModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
