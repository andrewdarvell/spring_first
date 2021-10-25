import {Component,  OnInit, } from '@angular/core';
import {CategoryFlat,} from '../../../model/user-shop';
import {FormBuilder, } from '@angular/forms';
import {UserShopService} from '../../../service/user-shop.service';
import {AdminShopService} from '../../../service/admin-shop.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: "app-full-product-editor",
  templateUrl: "full-product-editor.component.html",
  styleUrls: ["full-product-editor.component.scss"]
})
export class FullProductEditorComponent implements OnInit {

  categories: CategoryFlat[] = [];
  productId: number = 0;


  constructor(private fb: FormBuilder,
              private shopService: UserShopService,
              private adminShopService: AdminShopService,
              public snackBar: MatSnackBar,
              public route: ActivatedRoute,) {
  }

  ngOnInit(): void {
    this.shopService.getCategoriesFlat().subscribe(resp => this.categories = resp);
    this.route.paramMap.subscribe(params => {
      this.productId = Number(params.get('productId'));
    });
  }



}
