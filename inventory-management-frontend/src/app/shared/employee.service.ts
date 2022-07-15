import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employee } from '../model/employee';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService extends DataService<Employee>{

  constructor(protected override httpClient: HttpClient) { 
    super(httpClient);
  }

  getResourceUrl(): string {
    return "employees";
  }
}
