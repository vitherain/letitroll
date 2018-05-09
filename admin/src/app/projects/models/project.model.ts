import { Environment } from '../../environments/models/environment.model';

export class Project {
  constructor(
    private _id: string,
    private _version: number,
    private _name: string,
    private _environments: Array<Environment>
  ) {}

  get id(): string {
    return this._id;
  }

  get version(): number {
    return this._version;
  }

  get name(): string {
    return this._name;
  }

  get environments(): Array<Environment> {
    return this._environments;
  }
}
