export class Environment {
  private readonly _id: string;
  private readonly _version: number;
  private readonly _name: string;

  constructor(id: string, version: number, name: string) {
    this._id = id;
    this._version = version;
    this._name = name;
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
}
