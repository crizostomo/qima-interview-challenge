import { Component, OnInit } from '@angular/core';
import { Product } from './product.model';
import { ProductService, FilterParams } from '../service/product.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  displayedColumns: string[] = ['name', 'price', 'actions'];
  filterForm: FormGroup;

  constructor(
    private service: ProductService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.filterForm = this.fb.group({
      name: [''],
      minPrice: [''],
      maxPrice: ['']
    });
  }

  ngOnInit() {
    this.load();
  }

  load() {
    const filters = this.filterForm.value;
    this.service.getProducts(0, 100, filters).subscribe(data => {
      this.products = data.content;
    });
  }

  applyFilter() {
    this.load();
  }

  resetFilter() {
    this.filterForm.reset();
    this.load();
  }

  edit(id?: number) {
    this.router.navigate(['/edit', id]);
  }

  delete(id?: number) {
    if (confirm('Are you sure?')) {
      this.service.deleteProduct(id!).subscribe(() => this.load());
    }
  }

  navigateToCreate() {
    this.router.navigate(['/create']);
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
