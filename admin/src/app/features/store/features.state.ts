import { Feature } from '../models/feature.model';
import { AppState } from '../../store/app.state';

export interface FeaturesState extends AppState {
  features: Features;
  sideNavOpened: boolean;
}

export interface Features {
  content: Feature[];
  totalElements: number;
}
