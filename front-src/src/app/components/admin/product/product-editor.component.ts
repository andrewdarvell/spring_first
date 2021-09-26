import {Component, EventEmitter, Input, OnInit, Output, SimpleChanges} from '@angular/core';
import {CategoryFlat, Product} from '../../../model/user-shop';
import {FormBuilder, Validators} from '@angular/forms';
import {UserShopService} from '../../../service/user-shop.service';
import {AdminShopService} from '../../../service/admin-shop.service';
import {environment} from '../../../../environments/environment';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: "app-product-editor",
  templateUrl: "product-editor.component.html",
  styleUrls: ["product-editor.component.scss"]
})
export class ProductEditorComponent implements OnInit {

  categories: CategoryFlat[] = [];

  productForm = this.fb.group({
    id: [],
    title: ['', Validators.required],
    cost: ['', Validators.required],
    categoryId: ['', Validators.required],
  })

  imageFile: any;

  @Input()
  public product = {} as Product;


  @Output()
  public resultProductEvent: EventEmitter<Product> = new EventEmitter();

  ngOnChanges(changes: SimpleChanges) {
    console.log(changes.product.currentValue);
    console.log("change")
    this.reloadForm();
  }

  reloadForm() {
    this.productForm.get("id")?.setValue(this.product.id);
    this.productForm.get("title")?.setValue(this.product.title);
    this.productForm.get("cost")?.setValue(this.product.cost);
    this.productForm.get("categoryId")?.setValue(this.product.categoryId);
  }

  constructor(private fb: FormBuilder,
              private shopService: UserShopService,
              private adminShopService: AdminShopService,
              public snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.shopService.getCategoriesFlat().subscribe(resp => this.categories = resp);
  }

  saveProduct() {
    if (this.isEditMode()) {
      this.adminShopService.updateProduct(this.productForm.value).subscribe(
        resp => {
          this.snackBar.open('Продукт добавлен', 'Ок', {duration: 3000});
          this.resultProductEvent.emit(resp);
        }
      )
    } else {
      this.adminShopService.addProduct(this.productForm.value).subscribe(
        resp => {
              this.snackBar.open('Продукт добавлен', 'Ок', {duration: 3000});
          this.resultProductEvent.emit(resp);
        }
      )
    }
  }

  initNew() {
    this.product = {} as Product;
    this.reloadForm();
  }


  cancel() {
    if (this.isEditMode()) {
      this.reloadForm();
    } else {
      this.product = {} as Product;
      this.productForm.reset();
    }
  }

  isEditMode() {
    return this.product.id !== undefined
  }

  onChange(event: any) {
    this.imageFile = event.target.files[0];
  }

  uploadImage() {
    if (this.isEditMode() && this.product.saveImageLink !== undefined) {
      this.adminShopService.uploadProductImage(this.imageFile, this.product.saveImageLink).subscribe(
        resp => {
          this.snackBar.open('Продукт обновлён', 'Ок', {duration: 3000});
          this.resultProductEvent.emit(resp);
        }
      )
    }
  }

  getProductImageLink(url: string): string {
    return `${environment.apiRootEndpoint}${url}`
  }


}
