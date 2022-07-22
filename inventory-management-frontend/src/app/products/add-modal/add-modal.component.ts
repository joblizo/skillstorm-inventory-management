import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Inventory } from 'src/app/model/inventory';
import { InventoryService } from 'src/app/shared/inventory.service';
import { ProductService } from 'src/app/shared/product.service';
import { mockCategory, mockSupplier, mockWarehouse, newProduct } from '../products.component';

@Component({
  selector: 'app-add-modal',
  templateUrl: './add-modal.component.html',
  styleUrls: ['./add-modal.component.css']
})
export class AddModalComponent implements OnInit {

  @Input() suppliers: mockSupplier[];
  @Input() categories: mockCategory[];
  @Input() warehouses: mockWarehouse[];
  @Input() currentProduct: newProduct;
  constructor(public activeModal: NgbActiveModal, public productService: ProductService, public inventoryService:InventoryService) { }

  ngOnInit(): void {
  }

  passBack() {
    this.activeModal.close(this.currentProduct);
    this.formInventory(this.currentProduct);
  }

  formInventory( currentProduct: newProduct)
  {
    let newInventory: Inventory = {
      id: 0,
      price: this.currentProduct.price,
      stock: this.currentProduct.stock,
      productId: this.currentProduct.id,
      warehouseId: this.currentProduct.warehouseId,
    }
    console.log(newInventory);
    this.inventoryService.post(newInventory).subscribe((response) => {
      console.log(response);
    });
  }

}
