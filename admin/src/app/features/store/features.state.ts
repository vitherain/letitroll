import { Feature } from '../models/feature.model';
import { AppState } from '../../store/app.state';

export interface FeaturesState extends AppState {
  features: State;
}

export interface State {
  content: Feature[];
  totalElements: number;
}
