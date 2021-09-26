import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {FormBuilder, Validators} from "@angular/forms";
import {UserShopService} from '../../../service/user-shop.service';
import {CategoryFlat} from '../../../model/user-shop';


export interface FilteringProductData {
  minCost: number,
  maxCost: number,
  title: string,
  categoryId: number,
}

@Component({
  selector: "app-product-filters",
  templateUrl: "filters.component.html",
  styleUrls: ["filters.component.scss"]
})
export class FiltersComponent implements OnInit {

  categories: CategoryFlat[] = [];

  filterForm = this.fb.group({
    minCost: [''],
    maxCost: [''],
    title: [''],
    categoryId: ['']
  })

  ngOnInit(): void {
    this.shopService.getCategoriesFlat().subscribe(resp => this.categories = resp);
  }

  constructor(private fb: FormBuilder,
              private shopService: UserShopService) {
  }

  @Output()
  processFilterAction: EventEmitter<FilteringProductData> = new EventEmitter();

  doFilter() {
    this.processFilterAction.emit(this.filterForm.value);
  }

  reset() {
    this.filterForm.reset();
    this.doFilter();
  }


}
