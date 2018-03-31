export interface TableRequestPayload {
  page?: number,
  size?: number,
  sort?: SortDefinition
}

interface SortDefinition {
  property: string,
  direction: string
}
