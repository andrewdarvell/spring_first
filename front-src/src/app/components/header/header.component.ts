import {Component} from "@angular/core";
import {Select, Store} from "@ngxs/store";
import {UserState} from "../../store/states/user.state";
import {Observable} from "rxjs";
import {StoreService} from '../../service/store.service';
import {StateReset} from 'ngxs-reset-plugin';

@Component({
  selector: "app-header",
  templateUrl: "header.component.html",
  styleUrls: ["header.component.scss"]
})
export class HeaderComponent {

  @Select(UserState.hasAdminRole) hasAdminRole$: Observable<boolean> | undefined;
  @Select(UserState.hasAnyRole) hasAnyRole$: Observable<boolean> | undefined;
  @Select(UserState.hasUserRole) hasUserRole$: Observable<boolean> | undefined;

  constructor(private store: Store,
              private storeService: StoreService,) {
  }


  logOut() {
    this.store.dispatch(
      new StateReset(UserState)
    );
    this.storeService.deleteToken();
  }


}
