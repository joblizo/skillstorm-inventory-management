import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { InventoryService } from 'src/app/shared/inventory.service';
import { ProductService } from 'src/app/shared/product.service';

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css']
})
export class DeleteModalComponent implements OnInit {
  @Input() currentInventoryId: number;
  @Input() currentProductId: number;
  @Input() isProduct: boolean;
  constructor(public activeModal: NgbActiveModal, public inventoryService: InventoryService, public productService: ProductService) { }

  ngOnInit(): void {
  }

  passBack() {
    this.activeModal.close();
    if (!this.isProduct) {
      this.deleteProductandInventory()
    }
    this.inventoryService.delete(this.currentInventoryId)
      .subscribe((response) => {
        console.log(response);
      });
  }

  deleteProductandInventory() {
    if (!this.isProduct) {
      this.inventoryService.delete(this.currentInventoryId)
        .subscribe((response) => {
          console.log(response);
        });
      this.productService.delete(this.currentProductId)
        .subscribe((response) => {
          console.log(response);
        });

    }
  }

}
