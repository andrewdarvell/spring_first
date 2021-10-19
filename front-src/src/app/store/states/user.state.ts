import {Action, Selector, State, StateContext} from "@ngxs/store";
import {Injectable} from "@angular/core";
import {AddUserRole} from "../actions/user.actions";

export class UserStateModel {
  roles: string[] = [];
}

export const ROLE_ADMIN = "ROLE_ADMIN";

@State<UserStateModel>({
  name: 'user_roles',
  defaults: {
    roles: []
  }
})
@Injectable()
export class UserState {

  @Selector()
  static roles(state: UserStateModel): string[] {
    return state.roles;
  }

  @Selector()
  static hasAdminRole(state: UserStateModel): boolean {
    return state.roles.filter(role => role === 'ROLE_ADMIN').length > 0;
  }

  @Selector()
  static hasUserRole(state: UserStateModel): boolean {
    return state.roles.filter(role => role === 'ROLE_USER').length > 0;
  }

  @Selector()
  static hasAnyRole(state: UserStateModel): boolean {
    return state.roles.length > 0;
  }

  @Action(AddUserRole)
  addUserRoles({getState, patchState}: StateContext<UserStateModel>, {payload}: AddUserRole) {
    const state = getState();
    patchState({
      roles: [...state.roles, payload]
    });
  }
}
