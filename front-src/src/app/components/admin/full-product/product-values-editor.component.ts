import {Component,  OnInit, } from '@angular/core';
import {ProductTypeValue,} from '../../../model/user-shop';
import {FormArray, FormBuilder, } from '@angular/forms';
import {UserShopService} from '../../../service/user-shop.service';
import {AdminShopService} from '../../../service/admin-shop.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: "app-full-product-editor",
  templateUrl: "product-values-editor.component.html",
  styleUrls: ["product-values-editor.component.scss"]
})
export class ProductValuesEditorComponent implements OnInit {

  productId: number = 0;
  values: ProductTypeValue[] = [];

  productValuesGroup = this.fb.group({
    valuesOnEdit: this.fb.array([])
  });

  get valuesOnEdit() {
    return this.productValuesGroup.get('valuesOnEdit') as FormArray;
  }


  constructor(private fb: FormBuilder,
              private shopService: UserShopService,
              private adminShopService: AdminShopService,
              public snackBar: MatSnackBar,
              public route: ActivatedRoute,) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.productId = Number(params.get('productId'));
      this.shopService.getAllValues(this.productId).subscribe(resp => {
        this.values = resp;
        this.buildForm();
      });
    });
  }

  buildForm() {
    this.valuesOnEdit.clear();
    this.productValuesGroup.reset();

    this.values.forEach(v => {
      this.valuesOnEdit.push(this.fb.group({
            id: [v.id],
            dictId: [v.dictId],
            title: [v.title],
            value: [v.value],
          }
      ));
    });
  }

  getValueTitle(index: number) {
    return this.valuesOnEdit.controls[index].get("title")?.value;
  }


  doSave() {
    if (this.productValuesGroup.valid) {
      this.adminShopService.saveAllValues(this.productId, this.valuesOnEdit.value).subscribe(resp => {
        console.log("success");
      });

    }
  }

}
