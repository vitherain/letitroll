import { Project } from '../../projects/models/project.model';
import { Environment } from '../../environments/models/environment.model';

export function findProject(projectName: string, projects: Array<Project>): Project {
  return projects.find((project: Project) => project.name.toLowerCase() === projectName);
}

export function findEnvironment(environmentName: string, environments: Array<Environment>): Environment {
  return environments.find((environment: Environment) => environment.name.toLowerCase() === environmentName);
}
