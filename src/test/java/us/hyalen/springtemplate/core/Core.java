package us.hyalen.springtemplate.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import us.hyalen.springtemplate.SpringTemplateApplication;

@ContextConfiguration (classes = SpringTemplateApplication.class)
@WebAppConfiguration
@SpringBootTest // (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class Core extends Overlay {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected ObjectMapper objectMapper;

    @Override
    public String insertSqlFilename() {
        return null;
    }

    @Override
    public String cleanupSqlFilename() {
        return null;
    }

    @BeforeAll
    protected void setup() {
        // mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
