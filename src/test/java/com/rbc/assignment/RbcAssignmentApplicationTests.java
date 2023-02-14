package com.rbc.assignment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc 
@SpringBootTest
class RbcAssignmentApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testValidFileUpload()
	  throws Exception {
		
		MockMultipartFile file = new MockMultipartFile("file", "dow_jones_index.pdf", "", "test".getBytes());
	    
	    mvc.perform(MockMvcRequestBuilders.multipart("/stockdata/task/upload")
                .file(file))
	    .andExpect(status().isOk());
           
	}

}
