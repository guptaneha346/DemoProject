package Demo;

import Demo.model.Employee;
import Demo.repository.EmployeeRepository;
import Demo.service.EmployeeServiceImpl;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoProjectApplicationTests {

	@Autowired
	private EmployeeServiceImpl emp;

	@MockBean
	private EmployeeRepository repo;

	@Test
	void contextLoads() {
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void fetchTest(){
		Mockito.when(repo.findAll()).thenReturn(Stream.of(new Employee(1,"jannat","jannat@gmail.com")).collect(
				Collectors.toList()));
		Map<String,Object> mapParam=null;
		Map<String,Object> param=initializeMapParam(mapParam);
		assertEquals(1,emp.getAllEmployee(param).getSize());
	}
	private Map<String,Object> initializeMapParam(Map<String,Object> params){
		params.put("currentPage",1);
		params.put("numPerPage",1);
		return params;
	}
}
