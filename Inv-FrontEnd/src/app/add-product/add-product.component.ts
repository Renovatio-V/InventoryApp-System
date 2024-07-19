import { Component } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';



@Component({
  selector: 'app-add-product', 
  standalone: true,  
  imports: [CommonModule, FormsModule],
  templateUrl: './add-product.component.html'
})
export class AddProductComponent {
  product: Product = new Product();

  constructor(private productService: ProductService, private router: Router){}

  onSubmit() {
    this.productService.addProduct(this.product).subscribe({
      next: (data) => {
        console.log('Product added successfully');
        this.router.navigate(['/products']);
      },
      error: (error) => {
        console.error('Error adding product', error);
      }
    });
  }
  
}
