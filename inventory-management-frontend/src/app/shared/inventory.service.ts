import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Inventory } from '../model/inventory';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class InventoryService extends DataService<Inventory>{

  constructor(protected override httpClient: HttpClient) { 
    super(httpClient);
  }

  getResourceUrl(): string {
    return "inventory/";
  }
}
