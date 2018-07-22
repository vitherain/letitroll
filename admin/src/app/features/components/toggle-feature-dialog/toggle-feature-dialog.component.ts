import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FeatureTargeting } from '../../models/feature-targeting.model';

@Component({
  selector: 'app-toggle-feature-dialog',
  templateUrl: './toggle-feature-dialog.component.html',
  styleUrls: ['./toggle-feature-dialog.component.scss']
})
export class ToggleFeatureDialogComponent implements OnInit {
  constructor(
    private dialogRef: MatDialogRef<ToggleFeatureDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { featureTargeting: FeatureTargeting }
  ) {}

  ngOnInit() {}
}
