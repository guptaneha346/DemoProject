package Demo;


import Demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void insertTest() throws Exception {
        Employee employee = new Employee("neha", "n@gmail.com");
        String jsonString = objectMapper.writeValueAsString(employee);
        mockMvc.perform(MockMvcRequestBuilders.post("/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json")
                .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
