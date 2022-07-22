import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee';
import { Warehouse } from '../model/warehouse';
import { EmployeeService } from '../shared/employee.service';
import { WarehouseService } from '../shared/warehouse.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  employee: Employee;
  warehouse: Warehouse;
  
  constructor(public employeeService:EmployeeService, public warehouseService:WarehouseService) { 
    this.getEmployee();
    this.getWarehouse();

    
  }

  ngOnInit(): void {
    
  }

  getEmployee(){
    return this.employeeService
    .get()
    .subscribe((employee: Employee) => {
      console.log(employee);
      this.employee = employee;
    });
  }

  getWarehouse(){
    return this.warehouseService
    .get()
    .subscribe((warehouse: Warehouse) => {
      this.warehouse = warehouse;
    });
  }
}
