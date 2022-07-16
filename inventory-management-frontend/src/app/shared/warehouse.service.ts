import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Warehouse } from '../model/warehouse';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class WarehouseService extends DataService<Warehouse> {

  constructor(protected override httpClient: HttpClient) { 
    super(httpClient); 
  }

  getResourceUrl(): string {
    return "warehouse";
  }
}
