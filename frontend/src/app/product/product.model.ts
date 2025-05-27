export interface Category {
  id: number;
  name: string;
}

export interface Product {
  id?: number;
  name: string;
  description?: string;
  price: number;
  category?: Category;
  code?: string;
  stockQuantity?: number;
  available?: boolean;
  imageUrl?: string;
  createdAt?: Date;
  updatedAt?: Date;
}

export interface PaginatedProducts {
  content: Product[];
  totalElements: number;
  totalPages: number;
  number: number;
  size: number;
}
