import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-delete-feature-dialog',
  templateUrl: './delete-feature-dialog.component.html',
  styleUrls: ['./delete-feature-dialog.component.scss']
})
export class DeleteFeatureDialogComponent {
  keySpecified$: Observable<boolean>;

  repeatKey: string;

  constructor(
    private dialogRef: MatDialogRef<DeleteFeatureDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any
  ) {}

  onCancel(): void {
    this.dialogRef.close(false);
  }
}
