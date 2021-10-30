import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {
  CategoryFlat,
  CostsResponse,
  FilterOrderRequest,
  FilterProductRequest,
  Order, OrderListResponse, Product,
  ProductListResponse, ProductTypeValue, Review
} from '../model/user-shop';
import {environment} from '../../environments/environment';
import {LoginResponse} from '../model/auth';

const product_prefix = '/product';
const category_prefix = '/category';
const user_prefix = '/user';
const order_prefix = '/order';
const product_info_prefix = '/info';
const grade_prefix = '/grade';

@Injectable({
  providedIn: 'root'
})
export class UserShopService {

  constructor(private http: HttpClient) {
  }

  public getProducts(request: FilterProductRequest): Observable<ProductListResponse> {
    return this.http.post<ProductListResponse>(`${environment.apiEndpoint}${product_prefix}/list`, request);
  }

  public getProduct(productId: number): Observable<Product> {
    return this.http.get<Product>(`${environment.apiEndpoint}${product_prefix}/${productId}`);
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

  public getOrdersByCurrUser(request: FilterOrderRequest): Observable<OrderListResponse> {
    return this.http.post<OrderListResponse>(`${environment.apiEndpoint}${order_prefix}/by_current_user`, request);
  }

  public getAllValues(productId: number): Observable<ProductTypeValue[]> {
    return this.http.get<ProductTypeValue[]>(`${environment.apiEndpoint}${product_info_prefix}/by_product/${productId}`);
  }

  public sendReview(productId: number, review: Review): Observable<Review> {
    return this.http.post<Review>(`${environment.apiEndpoint}${grade_prefix}/review/${productId}`, review);
  }

  public getReviewsByProduct(productId: number): Observable<Review[]> {
    return this.http.get<Review[]>(`${environment.apiEndpoint}${grade_prefix}/review/by_product/${productId}`);
  }

}
