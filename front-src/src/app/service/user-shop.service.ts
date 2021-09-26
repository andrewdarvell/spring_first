import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CategoryFlat, FilterProductRequest, ProductListResponse} from '../model/user-shop';
import {environment} from '../../environments/environment';
import {LoginResponse} from '../model/auth';

const product_prefix = '/product';
const category_prefix = '/category';
const user_prefix = '/user';

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

}