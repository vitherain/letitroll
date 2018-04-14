import { NgModule } from '@angular/core';
import { CdkTableModule } from '@angular/cdk/table';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatIconModule,
  MatMenuModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSlideToggleModule,
  MatSortModule,
  MatTableModule,
  MatToolbarModule,
  MatTooltipModule
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
    MatProgressSpinnerModule,
    MatTableModule,
    MatToolbarModule,
    MatTooltipModule
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
    MatProgressSpinnerModule,
    MatTableModule,
    MatToolbarModule,
    MatTooltipModule
  ]
})
export class MaterialModule {}
