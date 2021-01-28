package gomining.studentsnotes;

import gomining.studentsnotes.domain.StudentNote;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StudentsNotesApplication.class)
@WebAppConfiguration
public class StudentNoteIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(context)
                .build();
    }

    @Test
    public void studentNoteApiShouldReturn200toGetAllCall() throws Exception {
        mockMvc.perform(get("/note/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void StudentNoteApiShouldPostNewNoteAndReturn200() throws Exception {
        StudentNote studentNote = new StudentNote();
        String id = "534bhj34b3";
        studentNote.setId(id);
        studentNote.setNote(6.9);
        studentNote.setStudent("Pedro");

        String json = "    {\n" +
                "    \"id\": \"h5j34h53454c\",\n" +
                "    \"name\": \"Pedro\",\n" +
                "    \"note\": 6.9\n" +
                "    }";

        mockMvc.perform(post("/note")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
    }
}
