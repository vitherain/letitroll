import { Action } from '@ngrx/store';
import { GoPayload } from '../shared/store/go-payload';

export const GO = '[ROUTER] GO';
export const BACK = '[ROUTER] BACK';
export const FORWARD = '[ROUTER] FORWARD';

export class Go implements Action {
  readonly type = GO;

  constructor(public payload: GoPayload) {}
}

export class Back implements Action {
  readonly type = BACK;
}

export class Forward implements Action {
  readonly type = FORWARD;
}
