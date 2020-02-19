package Demo.service;

import Demo.model.Employee;
import Demo.response.Response;

public interface EmployeeService {
    public Response create(Employee emp);
}
