import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CategoryFlat, CostsResponse, FilterProductRequest, Order, ProductListResponse} from '../model/user-shop';
import {environment} from '../../environments/environment';
import {LoginResponse} from '../model/auth';

const product_prefix = '/product';
const category_prefix = '/category';
const user_prefix = '/user';
const order_prefix = '/order';

@Injectable({
  providedIn: 'root'
})
export class UserShopService {

  constructor(private http: HttpClient) {
  }

  public getProducts(request: FilterProductRequest): Observable<ProductListResponse> {
    return this.http.post<ProductListResponse>(`${environment.apiEndpoint}${product_prefix}/list`, request);
  }

  public getCategoriesFlat(): Observable<CategoryFlat[]> {
    return this.http.get<CategoryFlat[]>(`${environment.apiEndpoint}${category_prefix}`)
  }

  public authUser(login: string, password: string): Observable<LoginResponse> {
    const formData: FormData = new FormData();
    formData.append('login', login);
    formData.append('password', password);
    return this.http.post<LoginResponse>(`${environment.apiEndpoint}${user_prefix}/auth`, formData);
  }

  public getRoles(): Observable<string[]> {
    return this.http.get<string[]>(`${environment.apiEndpoint}${user_prefix}/roles`);
  }

  public getActualCosts(productIds: (number | undefined) [] | undefined):Observable<CostsResponse> {
    return this.http.post<CostsResponse>(`${environment.apiEndpoint}${product_prefix}/costs`, productIds);
  }

  public addOrder(order: Order):Observable<Order>{
    return this.http.post<Order>(`${environment.apiEndpoint}${order_prefix}`, order);
  }

}
