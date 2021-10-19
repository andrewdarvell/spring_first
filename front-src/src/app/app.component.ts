import {Component, OnInit} from '@angular/core';
import {Store} from '@ngxs/store';
import {StoreService} from './service/store.service';
import {AddUserRole} from './store/actions/user.actions';
import {UserShopService} from './service/user-shop.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  title = 'front-src';

  constructor(private store: Store,
              private storeService: StoreService,
              private shopService: UserShopService,) {
  }

  ngOnInit(): void {
    const token = this.storeService.getToken();
    if (token) {
      this.shopService.getRoles().subscribe(resp =>{
        resp.forEach(role => console.log(role));
        resp.forEach(role => this.store.dispatch(new AddUserRole(role)));
      });
    }
  }
}
