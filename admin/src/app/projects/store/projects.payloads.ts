import { Project } from '../models/project.model';

export interface LoadProjectsSuccessPayload {
  content: Array<Project>;
  totalElements: number;
}
