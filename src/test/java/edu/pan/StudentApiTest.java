package edu.pan;
import org.junit.jupiter.api.*; import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest; import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc; import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class StudentApiTest {
 @Autowired MockMvc mvc; @Autowired ObjectMapper json; @Autowired StudentRepository repo;
 @BeforeEach void reset(){repo.deleteAll();}
 String body="{\"name\":\"Pan Eduard\",\"groupName\":\"IT2-2312\",\"email\":\"pan@example.com\"}";
 @Test void lifecycle() throws Exception {
 String out=mvc.perform(post("/api/students").contentType("application/json").content(body)).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
 String url="/api/students/"+json.readTree(out).get("id").asLong();
 mvc.perform(get("/api/students")).andExpect(jsonPath("$[0].name").value("Pan Eduard"));
 mvc.perform(get(url)).andExpect(status().isOk());
 mvc.perform(put(url).contentType("application/json").content(body.replace("IT2-2312","IT2-2312-updated"))).andExpect(jsonPath("$.groupName").value("IT2-2312-updated"));
 mvc.perform(delete(url)).andExpect(status().isNoContent()); mvc.perform(get(url)).andExpect(status().isNotFound());
 }
 @Test void invalid() throws Exception {mvc.perform(post("/api/students").contentType("application/json").content("{}")).andExpect(status().isBadRequest());}
 @Test void missing() throws Exception {mvc.perform(delete("/api/students/999999")).andExpect(status().isNotFound());mvc.perform(put("/api/students/999999").contentType("application/json").content(body)).andExpect(status().isNotFound());}
}
