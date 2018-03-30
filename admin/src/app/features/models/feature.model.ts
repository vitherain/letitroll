export class Feature {

  private readonly _id: string;
  private readonly _version: number;
  private readonly _name: string;

  constructor(id: string, version: number, name: string) {
    this._id = id;
    this._version = version;
    this._name = name;
  }

  get id(): string {
    console.log('Called get id()');
    return this._id;
  }

  get version(): number {
    console.log('Called get version()');
    return this._version;
  }

  get name(): string {
    console.log('Called get name()');
    return this._name;
  }
}
