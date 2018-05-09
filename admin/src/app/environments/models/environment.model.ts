export class Environment {
  constructor(private _id: string, private _version: number, private _name: string) {}

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
