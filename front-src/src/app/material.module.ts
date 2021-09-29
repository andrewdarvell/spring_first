
import {NgModule} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatRadioModule} from '@angular/material/radio';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatStepperModule} from '@angular/material/stepper';
import {MatIconModule} from '@angular/material/icon';
import {MatTabsModule} from '@angular/material/tabs';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatSliderModule} from '@angular/material/slider';
import {MatTooltipModule} from '@angular/material/tooltip';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {TextFieldModule} from '@angular/cdk/text-field';
import {MatMenuModule} from '@angular/material/menu';
import {MatTreeModule} from "@angular/material/tree";
import {ScrollingModule} from "@angular/cdk/scrolling";
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSelectModule} from '@angular/material/select';

@NgModule({
    imports: [MatSelectModule, MatPaginatorModule, MatButtonModule, MatCardModule, MatFormFieldModule, MatMenuModule, MatTreeModule,ScrollingModule,
        MatInputModule, MatToolbarModule, MatSidenavModule, MatListModule, MatTableModule, MatSnackBarModule,
        MatCheckboxModule, MatDividerModule, MatProgressSpinnerModule, MatRadioModule, MatExpansionModule, MatStepperModule,
        MatIconModule, MatTabsModule, MatAutocompleteModule, MatSliderModule, MatTooltipModule, DragDropModule, TextFieldModule],
    exports: [MatSelectModule, MatPaginatorModule, MatButtonModule, MatCardModule, MatFormFieldModule, MatMenuModule, MatTreeModule,ScrollingModule,
        MatInputModule, MatToolbarModule, MatSidenavModule, MatListModule, MatTableModule, MatSnackBarModule,
        MatCheckboxModule, MatDividerModule, MatProgressSpinnerModule, MatRadioModule, MatExpansionModule, MatStepperModule,
        MatIconModule, MatTabsModule, MatAutocompleteModule, MatSliderModule, MatTooltipModule, DragDropModule, TextFieldModule]
})

export class MyOwnCustomMaterialModule {}
