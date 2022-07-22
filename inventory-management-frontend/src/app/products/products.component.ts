import { Component, Input, OnInit, Output } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Product } from '../model/product';
import { Inventory } from '../model/inventory';

import { InventoryService } from '../shared/inventory.service';
import { ProductService } from '../shared/product.service';
import { EditModalComponent } from './edit-modal/edit-modal.component';
import { Warehouse } from '../model/warehouse';
import { DeleteModalComponent } from './delete-modal/delete-modal.component';
import { AddModalComponent } from './add-modal/add-modal.component';

export interface mockProduct {
  Id: number,
  Name: string,
  Description: string,
  Price: number,
  CategoryId: number,
  SupplierId: number;
}

export interface mockWarehouse {
  Id: number,
  Capacity: number,
  Location: string,
  Status: number,
}

export interface mockCategory {
  Id: number,
  Name: string,
  Description: string,
  Stock: number,
  WarehouseId: number;
}

export interface mockSupplier {
  Id: number,
  Name: string,
  Address: string,
  Phone: string,
  Fax: string,
  Email: string
}

export interface newProduct {
  id: number;
  name: string;
  price: number;
  stock: number;
  supplierId: number
  categoryId: number;
  warehouseId: number;
  description: string;
}

export interface newInventory {
  id: number;
  name: string;
  price: number;
  stock: number;
  supplierId: number
  categoryId: number;
  warehouseId: number;
  description: string;
}

export interface response {
  id: number;
}

export interface productDetails {
  location: string;
  price: number;
  stock: number;
  totalPrice: string;
  inventoryId: number;
}

@Component({
  selector: 'ngbd-modal-content',
  templateUrl: './product.component.modal.html',
  styleUrls: ['./product.component.modal.css']
})

export class NgbdModalContent {
  @Input() suppliers: mockSupplier[];
  @Input() categories: mockCategory[];
  @Input() warehouses: mockWarehouse[];
  @Output() newProduct: newProduct;
  id: number;

  constructor(public activeModal: NgbActiveModal, public productService: ProductService, public inventoryService: InventoryService) {

  }
  passBack() {
    this.activeModal.close(this.newProduct);
    console.log("Form received")
    this.getIdFromResponse(this.formProduct(this.newProduct), this.id)
    console.log("Product Sent" + this.id);
    console.log(this.id)
    this.formInventory(this.id, this.newProduct)
    console.log("Inventory Sent")
  }

  getIdFromResponse(req: any, resp: number) {
    return this.productService
      .post(req)
      .subscribe((response) => {
        console.log(response)
        this.formInventory(response.id, this.newProduct)
        console.log(resp)
      });
  }

  formInventory(id: number, newProduct: newProduct) {
    let newInventory: Inventory = {
      id: 0,
      price: this.newProduct.price,
      stock: this.newProduct.stock,
      productId: id,
      warehouseId: this.newProduct.warehouseId,
    }
    console.log(newInventory);
    this.inventoryService.post(newInventory).subscribe((response) => {
      console.log(response);
    });
  }

  formProduct(newProduct: newProduct) {
    let product: Product = {
      id: 0,
      name: newProduct.name,
      description: newProduct.description,
      category_id: newProduct.categoryId,
      supplier_id: newProduct.supplierId,
    }
    return product;
  }

  ngOnInit() {
  }
}

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  HighlightRow: Number;
  HighlightProductRow: Number;
  currentProductId: number;
  currentWarehouse: mockWarehouse;
  currentProduct: Product;
  currentProductStock: number;
  currentProductPrice: number;
  currentSupplier: string;
  currentCategory: string;
  currentTotalStock: number = 0;
  currentInventoryId: number;
  productDetails: productDetails[] = []; //remove
  inventory: Inventory[] = [];
  products: Product[] = [];
  @Input() newProduct: newProduct;

  warehouses: mockWarehouse[] = [{
    "Id": 1,
    "Capacity": 5000,
    "Location": "Kenilworth, NJ",
    "Status": 1
  },
  {
    "Id": 2,
    "Capacity": 5000,
    "Location": "Cranford, NJ",
    "Status": 1
  }];

  categories: mockCategory[] = [{
    "Id": 1,
    "Name": "Shoes",
    "Description": "",
    "Stock": 5,
    "WarehouseId": 1
  }];

  suppliers: mockSupplier[] = [{
    "Id": 1,
    "Name": "Nike",
    "Address": "NY",
    "Phone": "9090909",
    "Fax": "",
    "Email": "asdas@gmail.com",
  },
  {
    "Id": 2,
    "Name": "Adidas",
    "Address": "NY",
    "Phone": "9090909",
    "Fax": "",
    "Email": "asdas@gmail.com",
  }];

  searchString = '';
  constructor(private modalService: NgbModal, public inventoryService: InventoryService, public productService: ProductService) {
    this.getInventory();
    this.getProducts();
  }

  getProducts() {
    return this.productService
      .get()
      .subscribe((products: any) => {
        this.products = <Product[]>products;
        console.log(this.products)
      });
  }

  getInventory() {
    return this.inventoryService
      .get()
      .subscribe((inventory: any) => {
        this.inventory = <Inventory[]>inventory;
      });
  }

  ngOnInit(): void {
  }

  openAddStock() {
    const modalRef = this.modalService.open(AddModalComponent, { size: 'lg' });
    modalRef.componentInstance.suppliers = this.suppliers;
    modalRef.componentInstance.categories = this.categories;
    modalRef.componentInstance.warehouses = this.warehouses;
    modalRef.componentInstance.currentProductId = this.currentProductId;
    this.newProduct = {
      id: this.currentProductId,
      name: this.currentProduct.name,
      stock: 0,
      price: this.currentProductPrice,
      supplierId: this.currentProduct.supplier_id,
      categoryId: this.currentProduct.category_id,
      warehouseId: 0,
      description: this.currentProduct.description
    }
    modalRef.componentInstance.currentProduct = this.newProduct;
    modalRef.componentInstance.closed(this.getInventory())
  }

  openDelete(isProduct: boolean) {
    if (!isProduct) {
      const modalRef = this.modalService.open(DeleteModalComponent, { size: 'sm' });
      modalRef.componentInstance.currentProductId = this.currentProductId;
      modalRef.componentInstance.currentInventoryId = this.currentInventoryId;
      modalRef.componentInstance.isProduct = isProduct;
    }
    else {
      const modalRef = this.modalService.open(DeleteModalComponent, { size: 'sm' });
      modalRef.componentInstance.currentInventoryId = this.currentInventoryId;
      modalRef.componentInstance.isProduct = isProduct;
      modalRef.componentInstance.closed(this.getInventory())
    }

  }

  openEdit() {
    const modalRef = this.modalService.open(EditModalComponent, { size: 'lg' });
    modalRef.componentInstance.suppliers = this.suppliers;
    modalRef.componentInstance.categories = this.categories;
    modalRef.componentInstance.warehouses = this.warehouses;
    console.log("current product ID: " + this.currentProductId)
    this.newProduct = {
      id: this.currentProductId,
      name: this.currentProduct.name,
      stock: this.currentProductStock,
      price: this.currentProductPrice,
      supplierId: this.currentProduct.supplier_id,
      categoryId: this.currentProduct.category_id,
      warehouseId: this.currentWarehouse.Id,
      description: this.currentProduct.description
    }
    modalRef.componentInstance.currentProduct = this.newProduct;
    modalRef.componentInstance.currentInventoryId = this.currentInventoryId;
  }

  openAdd() {
    const modalRef = this.modalService.open(NgbdModalContent, { size: 'lg' });
    modalRef.componentInstance.suppliers = this.suppliers;
    modalRef.componentInstance.categories = this.categories;
    modalRef.componentInstance.warehouses = this.warehouses;
    this.newProduct = {
      id: 0,
      name: "",
      stock: 0,
      price: 0,
      supplierId: 0,
      categoryId: 0,
      warehouseId: 0,
      description: ""
    }
    modalRef.componentInstance.newProduct = this.newProduct;
  }

  getTotalPrice(price: number, stock: number) {
    return ((price * stock).toPrecision(6));
  }

  getProductDetails(product: number) {
    this.currentTotalStock = 0;
    if (this.productDetails.length > 0) {
      this.productDetails = [];
    }
    let tempArray: any[] = []
    for (var i of this.inventory) {
      if (i.productId == product) {
        tempArray.push(i)
      }
    }

    for (var j of tempArray) {
      this.currentTotalStock += j.stock;
      this.productDetails.push({
        "location": this.getWarehouse(j.warehouseId),
        "price": j.price,
        "stock": j.stock,
        "totalPrice": this.getTotalPrice(j.price, j.stock),
        "inventoryId": j.id
      })
    }
    // console.log(this.productDetails);
  }

  getCategory(productId: number) {
    for (let category of this.categories) {
      if (category.Id == productId) {
        return category.Name;
      }
    }
    return "";
  }

  getWarehouse(warehouseId: number) {
    for (let warehouse of this.warehouses) {
      if (warehouse.Id == warehouseId) {
        return warehouse.Location;
      }
    }
    return "";
  }

  getSupplier(supplierId: number) {
    for (let supplier of this.suppliers) {
      if (supplier.Id == supplierId) {
        return supplier.Name
      }
    }
    return "";
  }

  setProduct(productId: number) {

    this.currentProduct = this.products[productId];
    this.currentProductId = this.currentProduct.id;
    this.getProductDetails(this.currentProduct.id)
    this.HighlightRow = productId
    this.currentSupplier = this.getSupplier(this.currentProduct.supplier_id);
    this.currentCategory = this.getCategory(this.currentProduct.category_id);
  }

  setWarehouse(warehouseIndex: number, warehouseLocation: string) {
    this.HighlightProductRow = warehouseIndex;
    for (let warehouse of this.warehouses) {
      if (warehouse.Location == warehouseLocation) {
        this.currentWarehouse = warehouse;
      }
      this.currentProductStock = this.productDetails[warehouseIndex].stock
      this.currentProductPrice = this.productDetails[warehouseIndex].price
      this.currentInventoryId = this.productDetails[warehouseIndex].inventoryId
    }
    console.log(this.currentProduct, this.currentWarehouse, this.currentProductStock)
  }
}
