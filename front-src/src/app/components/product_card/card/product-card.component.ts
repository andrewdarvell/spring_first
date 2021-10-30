import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {UserShopService} from '../../../service/user-shop.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ActivatedRoute} from '@angular/router';
import {Product, ProductTypeValue, Review} from '../../../model/user-shop';
import {Select} from '@ngxs/store';
import {UserState} from '../../../store/states/user.state';
import {Observable} from 'rxjs';

@Component({
  selector: "app-product-card",
  templateUrl: "product-card.component.html",
  styleUrls: ["product-card.component.scss"]
})
export class ProductCardComponent implements OnInit{

  productId: number = 0;
  product?: Product;
  values: ProductTypeValue[] = [];
  reviews: Review[] = [];

  ratingFormGroup = this.fb.group({
    pluses: ['', Validators.required],
    minuses: ['', Validators.required],
    rating: [5, Validators.required],
    productId: [this.productId]
  })

  @Select(UserState.hasUserRole) hasUserRole$: Observable<boolean> | undefined;

  constructor(private shopService: UserShopService,
              public snackBar: MatSnackBar,
              public route: ActivatedRoute,
              private fb: FormBuilder) {
  }


  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.productId = Number(params.get('productId'));
      this.loadReviews();
      this.shopService.getProduct(this.productId).subscribe(resp => {
        this.product = resp;
      });
      this.shopService.getAllValues(this.productId).subscribe(resp => {
        this.values = resp;
      });
    });
  }

  loadReviews() {
    this.shopService.getReviewsByProduct(this.productId).subscribe(
      resp => this.reviews = resp
    );
  }

  formatLabel(value: number) {
    return value;
  }

  sendRating() {
    if (this.ratingFormGroup.valid){
      this.shopService.sendReview(this.productId, this.ratingFormGroup.value).subscribe(resp => {
        this.loadReviews();
      });
    }
  }

}
