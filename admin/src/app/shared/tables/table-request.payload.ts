import { HttpParams } from '@angular/common/http';

export interface TableRequestPayload {
  page?: number,
  size?: number,
  sort?: Array<SortDefinition>
}

interface SortDefinition {
  property: string,
  direction: string
}

export function toHttpParams(reqPayload: TableRequestPayload = {}): HttpParams {
  let params = new HttpParams();

  if (reqPayload.page || reqPayload.page === 0) {
    params = params.append('page', `${reqPayload.page}`);
  }
  if (reqPayload.size) {
    params = params.append('size', `${reqPayload.size}`);
  }
  if (reqPayload.sort && reqPayload.sort.length) {
    reqPayload.sort.forEach(oneSort => {
      params = params.append('sort', `${oneSort.property},${oneSort.direction}`);
    });
  }
  return params;
}
