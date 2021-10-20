export interface FilterProductRequest {
  currPage?: number;
  pageSize?: number;

  minCost?: number;
  maxCost?: number;
  categoryId?: number;
  title?: string;
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

export interface CostsResponse{
  costs : any;
}


export interface Order {
  deliveryAddress: string,
  comment: string,
  shopOrderItems: OrderItem[]
}

export interface OrderItem {
  productId: number,
  count: number
}
