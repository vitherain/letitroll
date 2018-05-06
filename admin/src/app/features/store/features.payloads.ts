import { Feature } from '../models/feature.model';

export interface LoadFeaturesSuccessPayload {
  content: Array<Feature>;
  totalElements: number;
}
