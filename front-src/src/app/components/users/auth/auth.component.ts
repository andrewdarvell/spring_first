import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {UserShopService} from '../../../service/user-shop.service';
import {StoreService} from '../../../service/store.service';
import {Router} from '@angular/router';

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
              private router: Router,) {
  }

  public onLoginClick() {
    this.isErrorLogin = false;
    if (this.loginForm.valid) {
      this.shopService.authUser(this.loginForm.get("login")?.value, this.loginForm.get("password")?.value)
        .subscribe(resp => {
          this.storeService.saveToken(resp.token);
          this.router.navigate(['/app/catalog']);
          // if (this.returnUrl) {
          //   this.router.navigateByUrl(this.returnUrl);
          // } else {
          //   this.router.navigate(['app/']);
          // }
        }, err => {
          if (err.status === 404) {
            this.isErrorLogin = true;
          }
        });
    }
  }


}
