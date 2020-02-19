package Demo.controller;

import Demo.model.Employee;
import Demo.response.Response;
import Demo.service.EmployeeServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

  @Autowired
  EmployeeServiceImpl service;

  @PostMapping("/insert")
  public synchronized Response<?> insert(@RequestBody Employee emp){
    Response response=service.create(emp);
    return response;
  }
  @GetMapping("/fetchAllEmployee")
  public Page<Employee> fetchAllEmployee(@RequestParam Map<String,Object> pageParam){
    Page<Employee> employee= service.getAllEmployee(pageParam);
    return employee;
  }

  @GetMapping(value = "/findById/{id}")
  public Optional<Employee> findById(@PathVariable("id") Integer id){
    Optional<Employee> emp=service.findById(id);
    return emp;
  }

  /*@PutMapping("/update")
  public Employee fetch(@RequestBody Employee emp){
    Employee employee= service.update(emp);
    return employee;
  }*/
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable("id") Integer id) {
   service.deleteById(id);
  }
}
