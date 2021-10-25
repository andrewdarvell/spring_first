export interface FilterProductRequest {
  currPage?: number;
  pageSize?: number;

  minCost?: number;
  maxCost?: number;
  categoryId?: number;
  title?: string;
}

export interface FilterOrderRequest {
  currPage?: number;
  pageSize?: number;

  userId?: string;
}

export interface Pageable {
  pageNumber: number;
  pageSize: number;
  paged: boolean;

}

export interface ProductListResponse {
  content: Product[];
  pageable: Pageable;
  totalPages: number;
  last: boolean;
  first: boolean;
  totalElements: number;
}

export interface OrderListResponse {
  content: Order[];
  pageable: Pageable;
  totalPages: number;
  last: boolean;
  first: boolean;
  totalElements: number;
}

export interface Product {
  id?: number;
  title: string;
  cost: number;
  categoryId: number;
  categoryName?: string;
  imageLink?: string;
  saveImageLink?: string;
}


export interface CategoryFlat {
  id?: number;
  title?: string;
  parentCategoryId?: number;
}

export interface CostsResponse {
  costs: any;
}


export interface Order {
  id?: number,
  deliveryAddress: string,
  comment: string,
  shopOrderItems: OrderItem[],
  status?: string;
}

export interface OrderItem {
  productId: number,
  count: number
}


export interface ProductType {
  id: number,
  title: string,
}

export interface DictValueType {
  id: number,
  title: string,
}

export interface ProductTypeDict {
  id?: number;
  title: string;
  sortOrder: number;
  dictValueTypeId: number;
}

