import { Feature } from './feature.model';

export class FeatureTargeting {
  constructor(
    private _id: string,
    private _version: number,
    private _feature: Feature,
    private _turnedOn: boolean,
    private _onValue: boolean,
    private _offValue: boolean
  ) {}

  get id(): string {
    return this._id;
  }

  get version(): number {
    return this._version;
  }

  get feature(): Feature {
    return this._feature;
  }

  get turnedOn(): boolean {
    return this._turnedOn;
  }

  get onValue(): boolean {
    return this._onValue;
  }

  get offValue(): boolean {
    return this._offValue;
  }
}
