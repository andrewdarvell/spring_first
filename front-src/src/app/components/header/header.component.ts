import {Component} from "@angular/core";
import {Select} from "@ngxs/store";
import {UserState} from "../../store/states/user.state";
import {Observable} from "rxjs";

@Component({
    selector: "app-header",
    templateUrl: "header.component.html",
    styleUrls: ["header.component.scss"]
})
export class HeaderComponent {

    @Select(UserState.hasAdminRole) hasAdminRole$: Observable<boolean> | undefined;


}
