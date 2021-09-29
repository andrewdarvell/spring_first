import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {UserShopService} from '../../../service/user-shop.service';
import {CategoryFlat} from '../../../model/user-shop';
import {AdminShopService} from '../../../service/admin-shop.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: "app-category-editor",
  templateUrl: "category-editor.component.html",
  styleUrls: ["category-editor.component.scss"]
})

export class CategoryEditorComponent implements OnInit {

  currentId = -1;

  public categories: CategoryFlat[] = [];
  columnsToDisplay = ['id', 'title', 'actions'];

  categoryForm = this.fb.group({
    title: ['', Validators.required]
  })

  constructor(private fb: FormBuilder,
              private shopService: UserShopService,
              private adminShopService: AdminShopService,
              public snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.reloadCategoryList();
  }

  reloadCategoryList(){
    this.shopService.getCategoriesFlat().subscribe(resp => this.categories = resp);
  }

  choose(id: number) {
    const category = this.categories.find(c => c.id === id);
    if (category) {
      this.currentId = id;
      this.categoryForm.setValue({title: category.title});
    }
  }

  delete(id: number) {
    this.adminShopService.deleteCategory(id).subscribe(
      () => this.reloadCategoryList(),
      () => {
        this.snackBar.open('Не удалось удалить. Категория используется', 'Ок', {duration: 3000});
      }
    )
  }

  save() {
    if (this.categoryForm.valid) {
      if (this.currentId === -1) {
        this.adminShopService.addCategory({title: this.categoryForm.get('title')?.value})
          .subscribe(resp => this.reloadCategoryList())
      } else {
        this.adminShopService.updateCategory({id:this.currentId, title: this.categoryForm.get('title')?.value})
          .subscribe(resp => this.reloadCategoryList())
      }
      this.reset();
    }
  }

  reset() {
    this.currentId = -1;
    this.categoryForm.reset();
  }

}
