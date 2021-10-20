import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {UserShopService} from '../../../service/user-shop.service';
import {StoreService} from '../../../service/store.service';
import {Router} from '@angular/router';
import {Store} from "@ngxs/store";
import {AddUserRole} from "../../../store/actions/user.actions";
import {StateReset} from 'ngxs-reset-plugin';
import {UserState} from '../../../store/states/user.state';

@Component({
  selector: "app-auth-form",
  templateUrl: "auth.component.html",
  styleUrls: ["auth.component.scss"]
})
export class AuthComponent {

  isErrorLogin = false;

  loginForm = this.fb.group({
    login: ['', Validators.required],
    password: ['', Validators.required],
  })

  constructor(private fb: FormBuilder,
              private shopService: UserShopService,
              private storeService: StoreService,
              private router: Router,
              private store: Store,) {
  }

  public onLoginClick() {
    this.store.dispatch(
      new StateReset(UserState)
    );

    this.isErrorLogin = false;
    if (this.loginForm.valid) {
      this.shopService.authUser(this.loginForm.get("login")?.value, this.loginForm.get("password")?.value)
        .subscribe(resp => {
          this.storeService.saveToken(resp.token);
          this.shopService.getRoles().subscribe(roleResp => {
            roleResp.forEach(role => console.log(role));
            roleResp.forEach(role => this.store.dispatch(new AddUserRole(role)));
          });
          this.router.navigate(['/app/catalog']);
        }, err => {
          if (err.status === 404) {
            this.isErrorLogin = true;
          }
        });
    }
  }


}
