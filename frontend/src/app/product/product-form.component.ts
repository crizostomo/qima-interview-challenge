import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../service/product.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  //styles: [`.full-width { width: 100%; margin-bottom: 16px; }`]
  styleUrls: ['./product-form.component.css'],
})
export class ProductFormComponent implements OnInit {
  form: FormGroup;
  isEditMode = false;
  id?: number;

  constructor(
    private fb: FormBuilder,
    private service: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      price: [0, Validators.required],
      code: [''],
      stockQuantity: [0],
      available: [true],
      imageUrl: [''],
    });
  }

  ngOnInit() {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.isEditMode = true;
      this.service.getProductById(this.id).subscribe((product) => {
        this.form.patchValue(product);
      });
    }
  }

  onSubmit() {
    if (this.form.invalid) return;
    const product = { ...this.form.value, id: this.id };
    const op = this.isEditMode
      ? this.service.updateProduct(product)
      : this.service.createProduct(product);

    op.subscribe(() => this.router.navigate(['/products']));
  }

  cancel() {
    this.router.navigate(['/products']);
  }
}
