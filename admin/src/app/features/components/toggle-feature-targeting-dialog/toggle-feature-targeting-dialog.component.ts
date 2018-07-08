import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FeatureTargeting } from '../../models/feature-targeting.model';

@Component({
  selector: 'app-toggle-feature-targeting-dialog',
  templateUrl: './toggle-feature-targeting-dialog.component.html',
  styleUrls: ['./toggle-feature-targeting-dialog.component.scss']
})
export class ToggleFeatureTargetingDialogComponent implements OnInit {
  constructor(
    private dialogRef: MatDialogRef<ToggleFeatureTargetingDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { featureTargeting: FeatureTargeting }
  ) {}

  ngOnInit() {}
}
