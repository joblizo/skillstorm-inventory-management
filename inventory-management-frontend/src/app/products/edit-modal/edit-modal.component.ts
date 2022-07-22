import { Component, Input, OnInit, Output } from '@angular/core';
import { NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { Inventory } from 'src/app/model/inventory';
import { Product } from 'src/app/model/product';
import { InventoryService } from 'src/app/shared/inventory.service';
import { ProductService } from 'src/app/shared/product.service';
import { mockCategory, mockSupplier, mockWarehouse, newProduct } from '../products.component';

@Component({
  selector: 'app-edit-modal',
  templateUrl: './edit-modal.component.html',
  styleUrls: ['./edit-modal.component.css']
})
export class EditModalComponent implements OnInit {
  @Input() suppliers: mockSupplier[];
  @Input() categories: mockCategory[];
  @Input() warehouses: mockWarehouse[];
  @Input() currentProduct: newProduct;
  @Input() currentInventoryId: number;
  id: number;
  
  constructor(public activeModal: NgbActiveModal, public productService: ProductService, public inventoryService:InventoryService) { }

  ngOnInit(): void {
    console.log("prod id at start: " + this.currentProduct.id);
  }

  passBack() {
    this.activeModal.close(this.currentProduct);
    console.log("Form received")
    this.getIdFromResponse(this.formProduct(), this.id)
    console.log("Product Sent" + this.id);
    console.log(this.id)
    this.formInventory(this.id, this.currentProduct)
    console.log("Inventory Sent")
  }

  getIdFromResponse(req:any,resp:number){
    return this.productService
    .put(req)
    .subscribe((response) => {
      console.log(response)
      this.formInventory(response.id, this.currentProduct)
      console.log(resp)
    });
  }

  formInventory(id:number, newProduct: newProduct)
  {
    let newInventory: Inventory = {
      id:this.currentInventoryId,
      price: this.currentProduct.price,
      stock: this.currentProduct.stock,
      productId: this.currentProduct.id,
      warehouseId: this.currentProduct.warehouseId,
    }
    console.log(newInventory);
    this.inventoryService.put(newInventory).subscribe((response) => {
      console.log(response);
    });
  }

  formProduct() {
    let product: Product = {
      id: this.currentProduct.id,
      name: this.currentProduct.name,
      description: this.currentProduct.description,
      category_id: this.currentProduct.categoryId,
      supplier_id: this.currentProduct.supplierId,
    }
    return product;
  }


}


