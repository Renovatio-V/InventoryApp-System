import { Component } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [],
  templateUrl: './product-list.component.html'  
})
export class ProductListComponent {
  products: Product[];

  constructor(private productService: ProductService, private enrouter: Router){  }

  ngOnInit(){
    //Load all the products
    this.getProducts();
  }

  private getProducts(){
    //Consume the observable data (Subscribe)
    this.productService.getProductsList().subscribe(
      (data => {
        this.products = data;
      })
    )
  }

  editProduct(id: number){
    this.enrouter.navigate(['edit-product', id])
  }

  deleteProduct(id: number){
    this.productService.deleteProduct(id).subscribe(
      {
        next: (data) => { this.getProducts() }, 
        error: (errors) => { console.log(errors)}
      }      
    )
    
  }

  
}
