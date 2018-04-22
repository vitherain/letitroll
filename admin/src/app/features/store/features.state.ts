import { Feature } from '../models/feature.model';
import { AppState } from '../../store/app.state';
import { Project } from '../../projects/models/project.model';

export interface FeaturesState extends AppState {
  projects: Project[];
  features: FeaturesState;
}

export interface FeaturesState {
  content: Feature[];
  totalElements: number;
}
