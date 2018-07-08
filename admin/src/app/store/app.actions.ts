import { Action } from '@ngrx/store';
import { GoPayload } from '../shared/store/go-payload';

export class Go implements Action {
  readonly type = '[ROUTER] GO';

  constructor(public payload: GoPayload) {}
}

export class Back implements Action {
  readonly type = '[ROUTER] BACK';
}

export class Forward implements Action {
  readonly type = '[ROUTER] FORWARD';
}
