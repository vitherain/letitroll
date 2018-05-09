import { User } from '../../users/models/user.model';

export type FeatureType = 'BOOLEAN' | 'MULTIVARIATE';

export class FeatureTag {
  constructor(private name: String) {}
}

export class Feature {
  constructor(
    private _id: string,
    private _addedTime: string,
    private _version: number,
    private _name: string,
    private _key: string,
    private _description: String,
    private _maintainer: User,
    private _permanent: boolean,
    private _tags: Array<FeatureTag>,
    private _type: FeatureType,
    private _availableToClient: boolean,
    private _projectId: string
  ) {}

  get id(): string {
    return this._id;
  }

  get addedTime(): string {
    return this._addedTime;
  }

  get version(): number {
    return this._version;
  }

  get name(): string {
    return this._name;
  }

  get key(): string {
    return this._key;
  }

  get description(): String {
    return this._description;
  }

  get maintainer(): User {
    return this._maintainer;
  }

  get permanent(): boolean {
    return this._permanent;
  }

  get tags(): Array<FeatureTag> {
    return this._tags;
  }

  get type(): FeatureType {
    return this._type;
  }

  get availableToClient(): boolean {
    return this._availableToClient;
  }

  get projectId(): string {
    return this._projectId;
  }
}
