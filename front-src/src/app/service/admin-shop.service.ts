import {Injectable} from '@angular/core';
import {CategoryFlat, DictValueType, Product, ProductType, ProductTypeDict,} from '../model/user-shop';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';

const product_prefix = '/admin/product';
const category_prefix = '/admin/category';
const user_prefix = '/admin/user';
const product_info_prefix = '/admin/info/';

@Injectable({
  providedIn: 'root'
})
export class AdminShopService {

  constructor(private http: HttpClient) {
  }

  public addCategory(request: CategoryFlat): Observable<CategoryFlat> {
    return this.http.post<CategoryFlat>(`${environment.apiEndpoint}${category_prefix}`, request);
  }

  public updateCategory(request: CategoryFlat): Observable<CategoryFlat> {
    return this.http.put<CategoryFlat>(`${environment.apiEndpoint}${category_prefix}`, request);
  }

  public deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiEndpoint}${category_prefix}/${id}`);
  }

  public updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(`${environment.apiEndpoint}${product_prefix}`, product);
  }

  public addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${environment.apiEndpoint}${product_prefix}`, product);
  }

  public uploadProductImage(file: any, link: string): Observable<any> {
    const formData = new FormData();
    formData.append("image", file, file.name);
    return this.http.post(`${environment.apiRootEndpoint}${link}`, formData)
  }

  public getAllTypesFiltered(title: string): Observable<ProductType[]> {
    const formData = new FormData();
    formData.append("title", title);
    return this.http.get<ProductType[]>(`${environment.apiEndpoint}${product_info_prefix}/type&title=${title}`);
  }

  public getAllTypes(): Observable<ProductType[]> {
    return this.http.get<ProductType[]>(`${environment.apiEndpoint}${product_info_prefix}/type`);
  }

  public getAllDictValueTypes(): Observable<DictValueType[]> {
    return this.http.get<DictValueType[]>(`${environment.apiEndpoint}${product_info_prefix}/type/value/dict`);
  }

  public getProductTypeDicts(productTypeId: number): Observable<ProductTypeDict[]> {
    return this.http.get<ProductTypeDict[]>(`${environment.apiEndpoint}${product_info_prefix}/type/${productTypeId}/dict`);
  }

  public updateDict(productTypeId: number, productTypeDict: ProductTypeDict[]): Observable<ProductTypeDict[]> {
    return this.http.put<ProductTypeDict[]>(`${environment.apiEndpoint}${product_info_prefix}/type/${productTypeId}/dict`, productTypeDict);
  }

  public updateProductType(productType: ProductType): Observable<ProductType> {
    return this.http.post<ProductType>(`${environment.apiEndpoint}${product_info_prefix}/type`, productType);
  }
}
