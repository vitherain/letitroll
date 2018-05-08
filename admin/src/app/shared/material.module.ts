import { NgModule } from '@angular/core';
import { CdkTableModule } from '@angular/cdk/table';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSidenavModule,
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
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatCheckboxModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatPaginatorModule,
    MatSidenavModule,
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
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatCheckboxModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatPaginatorModule,
    MatSidenavModule,
    MatSlideToggleModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatToolbarModule,
    MatTooltipModule
  ]
})
export class MaterialModule {}
