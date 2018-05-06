import { Feature } from '../models/feature.model';
import { AppState } from '../../store/app.state';

export interface FeaturesState extends AppState {
  features: Features;
}

export interface Features {
  content: Array<Feature>;
  totalElements: number;
  loading: boolean;
  sideNavOpened: boolean;
}
