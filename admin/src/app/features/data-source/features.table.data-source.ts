import { MatTableDataSource } from '@angular/material';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

export class FeaturesTableDataSource<T> extends MatTableDataSource<T> {

  _updateChangeSubscription(): void {
    debugger;
    super._updateChangeSubscription();
  }

  _filterData(data: T[]): T[] {
    debugger;
    return super._filterData(data);
  }

  _orderData(data: T[]): T[] {
    debugger;
    return super._orderData(data);
  }

  _pageData(data: T[]): T[] {
    debugger;
    return super._pageData(data);
  }

  _updatePaginator(filteredDataLength: number): void {
    debugger;
    super._updatePaginator(filteredDataLength);
  }

  connect(): BehaviorSubject<T[]> {
    debugger;
    return super.connect();
  }

  disconnect(): void {
    debugger;
    super.disconnect();
  }
}
