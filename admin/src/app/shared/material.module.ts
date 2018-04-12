import { NgModule } from '@angular/core';
import { CdkTableModule } from '@angular/cdk/table';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatIconModule,
  MatMenuModule,
  MatPaginatorModule,
  MatSlideToggleModule,
  MatSortModule,
  MatTableModule,
  MatToolbarModule
} from '@angular/material';

@NgModule({
  imports: [
    CdkTableModule,
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule,
    MatMenuModule,
    MatPaginatorModule,
    MatSlideToggleModule,
    MatSortModule,
    MatTableModule,
    MatToolbarModule
  ],
  exports: [
    CdkTableModule,
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule,
    MatMenuModule,
    MatPaginatorModule,
    MatSlideToggleModule,
    MatSortModule,
    MatTableModule,
    MatToolbarModule
  ]
})
export class MaterialModule { }
