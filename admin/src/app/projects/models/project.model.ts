import { Environment } from '../../environments/models/environment.model';

export class Project {
  private readonly _id: string;
  private readonly _version: number;
  private readonly _name: string;
  private readonly _environments: Environment[];

  constructor(id: string, version: number, name: string, environments: Environment[]) {
    this._id = id;
    this._version = version;
    this._name = name;
    this._environments = environments;
  }

  get id(): string {
    return this._id;
  }

  get version(): number {
    return this._version;
  }

  get name(): string {
    return this._name;
  }

  get environments(): Environment[] {
    return this._environments.slice();
  }
}
