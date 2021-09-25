import {Component} from "@angular/core";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
    selector: "app-product-filters",
    templateUrl: "filters.component.html",
    styleUrls: ["filters.component.scss"]
})
export class FiltersComponent {
    filterForm = this.fb.group({
        minCost: [''],
        maxCost: [''],
        title: ['']
    })

    constructor(public fb: FormBuilder,) {
    }

    doFilter(){

    }
}
