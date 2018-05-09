export class User {
  constructor(private _id: string, private _version: number, private _username: string) {}

  get id(): string {
    return this._id;
  }

  get version(): number {
    return this._version;
  }

  get username(): string {
    return this._username;
  }
}
