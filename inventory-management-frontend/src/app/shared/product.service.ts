import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService extends DataService<Product> {
  constructor(protected override httpClient: HttpClient) { 
    super(httpClient);
  }

  getResourceUrl(): string {
    return "product/";
  }
}
