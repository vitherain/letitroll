import { AppState } from '../../store/app.state';
import { Project } from '../models/project.model';

export interface ProjectsState extends AppState {
  projects: Projects;
}

export interface Projects {
  content: Project[];
  totalElements: number;
}