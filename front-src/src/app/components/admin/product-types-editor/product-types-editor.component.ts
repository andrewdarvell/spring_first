import {Component, Inject, OnInit} from '@angular/core';
import {DictValueType, ProductType, ProductTypeDict} from '../../../model/user-shop';
import {FormArray, FormBuilder, Validators} from '@angular/forms';
import {UserShopService} from '../../../service/user-shop.service';
import {AdminShopService} from '../../../service/admin-shop.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: "app-product-type-editor",
  templateUrl: "product-types-editor.component.html",
  styleUrls: ["product-types-editor.component.scss"]
})
export class ProductTypesEditorComponent implements OnInit {

  productTypes: ProductType[] = [];
  dictValueTypes: DictValueType[] = [];
  productTypeDict: ProductTypeDict[] = [];

  activeTypeId = -1;

  productDictGroup = this.fb.group({
    dictsOnEdit: this.fb.array([])
  });

  get dictsOnEdit() {
    return this.productDictGroup.get('dictsOnEdit') as FormArray;
  }

  constructor(private fb: FormBuilder,
              private shopService: UserShopService,
              private adminShopService: AdminShopService,
              public snackBar: MatSnackBar,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.adminShopService.getAllTypes().subscribe(resp => this.productTypes = resp);
    this.adminShopService.getAllDictValueTypes().subscribe(resp => this.dictValueTypes = resp);
  }

  doFilter() {
    this.snackBar.open('Фильтер в разработке', 'Ок', {duration: 3000});
  }

  addNewDict() {
    this.dictsOnEdit.push(this.fb.group({
        id: [-1],
        title: ['', Validators.required],
        sortOrder: [10],
        dictValueTypeId: [Validators.required],
      }
    ));
  }

  saveType() {
    if (this.productDictGroup.valid) {
      this.adminShopService.updateDict(this.activeTypeId, this.dictsOnEdit.value)
        .subscribe(resp => this.snackBar.open('Сохранено', 'Ок', {duration: 3000}));
    }
  }

  choseType(id: number) {
    this.adminShopService.getProductTypeDicts(id).subscribe(resp => {
      this.activeTypeId = id;
      this.productTypeDict = resp;
      this.dictsOnEdit.clear();
      this.productDictGroup.reset();

      this.productTypeDict.forEach(d => {
        this.dictsOnEdit.push(this.fb.group({
            id: [d.id],
            title: [d.title, Validators.required],
            sortOrder: [d.sortOrder],
            dictValueTypeId: [d.dictValueTypeId, Validators.required],
          }
        ));
      });
    });
  }


  openDialog(): void {
    const dialogRef = this.dialog.open(NewTypeDialog, {
      width: '500px',
      data: {typeTitle: ""}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result && result.length > 0) {
        this.adminShopService.updateProductType({id: -1, title: result}).subscribe(resp => {
          this.adminShopService.getAllTypes().subscribe(resp1 => this.productTypes = resp1);
          this.choseType(resp.id);
        });
      }
    });
  }
}

export interface DialogData {
  typeTitle: string;
}


@Component({
  selector: 'app-product-editor-new-type-dialog',
  templateUrl: 'product-types-editor-dialog.html',
})
export class NewTypeDialog {

  constructor(
    public dialogRef: MatDialogRef<NewTypeDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
