package com.colak.springreactivetutorial.declarativeclient;

import java.util.List;

public record EmployeeClientResponse(List<Employee> employees) {
}
