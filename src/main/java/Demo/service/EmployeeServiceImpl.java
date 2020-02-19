package Demo.service;

import Demo.model.Employee;
import Demo.repository.EmployeeRepository;
import Demo.response.Response;
import Demo.response.ValidationErrorResponse;
import Demo.utils.Messages;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

  public static final String JSON_CURRENT_PAGE="currentPage";
  public static final String JSON_NUM_PER_PAGE="numPerPage";
  public static final int DEFAULT_CURRENT_PAGE=1;
  public static final int DEFAULT_NUM_PER_PAGE=5;

  @Autowired
   EmployeeRepository repo;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository repo
                          ) {
    this.repo = repo;}

  public Response create(Employee emp){
    if(emp.getName()==null || emp.getName().isEmpty()){
      return new
          ValidationErrorResponse(Employee.JSON_KEY_FULL_NAME, Messages.FULL_NAME_REQUIRED);
    }
    else if(emp.getEmail()==null || emp.getEmail().isEmpty() ) {
      return new
          ValidationErrorResponse(Employee.JSON_KEY_EMAIL,Messages.EMAIL_REQUIRED);
    }
    Employee empl=repo.save(emp);
    return new Response(empl,200,Messages.SUCCESS_MESSAGE);
  }

  public Page<Employee> getAllEmployee(Map<String,Object> param){
    int currentPage=DEFAULT_CURRENT_PAGE;
    int numPerPage=DEFAULT_NUM_PER_PAGE;
    if(param.containsKey(JSON_CURRENT_PAGE) && param.containsKey(JSON_NUM_PER_PAGE)){
         currentPage= Integer.parseInt(param.get(JSON_CURRENT_PAGE).toString());
         numPerPage= Integer.parseInt(param.get(JSON_NUM_PER_PAGE).toString());
    }
    Pageable pageable = PageRequest.of(currentPage-1,numPerPage);
    Page<Employee> empl= repo.findAll(pageable);
    return empl;
  }

/*  public Employee update(Employee emp){
    Employee em=new Employee();
    em.setId(emp.getId());
    em.setName(emp.getName());
    em.setEmail(emp.getEmail());
    repo.save(em);
    return em;
  }*/
  public void deleteById(int id){
    repo.deleteById(id);
  }

  public Optional<Employee> findById(Integer id) {
    Optional<Employee> emp=repo.findById(id);
    return emp;
  }
}
