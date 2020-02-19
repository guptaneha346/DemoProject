package Demo;

import Demo.model.Employee;
import Demo.repository.EmployeeRepository;
import Demo.response.Response;
import Demo.service.EmployeeService;
import Demo.service.EmployeeServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoProjectApplicationTests {

    @MockBean
    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl employeeServiceImpl;


    @Before
    public void setUp() throws Exception{
        employeeServiceImpl=new EmployeeServiceImpl(employeeRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void responseCreateTest() throws Exception {
        Employee employee = new Employee();
        Response expectedResponse = new Response(employee,200,"Success");
        employee.setName("neha");
        employee.setEmail("n@gmail.com");
        when(employeeRepository.save(employee)).thenReturn(employee);
        Response response = employeeServiceImpl.create(employee);
        Assert.assertEquals(response.getCode(), expectedResponse.getCode());
        Assert.assertEquals(response.getMessage(), expectedResponse.getMessage());
        Assert.assertEquals(response.getResult(),expectedResponse.getResult());

    }


}
