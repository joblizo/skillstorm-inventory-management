import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee';
import { EmployeeService } from '../shared/employee.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  employee: Employee;
  
  constructor(public dataService:EmployeeService) { 
    this.getEmployee();
  }

  ngOnInit(): void {
    
  }

  getEmployee(){
    return this.dataService
    .get()
    .subscribe((employee: Employee) => {
      this.employee = employee;
    });
  }

}
