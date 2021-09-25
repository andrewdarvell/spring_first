import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CatalogComponent} from "./components/catalog/catalog.component";

const routes: Routes = [
  {path: '', redirectTo: 'app/catalog', pathMatch: 'full'},
  // {path: 'login', component: LoginPageComponent},
  // {path: 'register', component: RegisterPageComponent},
  {path: "app", children:[
      {path: "catalog", component: CatalogComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
