package controllers;

import nl.les12vinylshopdto.les12vinylshopdto.Les12VinylshopDtoApplication;
import nl.les12vinylshopdto.les12vinylshopdto.dto.genre.GenreRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import javax.print.attribute.standard.Media;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Les12VinylshopDtoApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createGenre_returnsCreated() throws Exception {
        GenreRequestDTO result = new GenreRequestDTO();
        result.setName("Rock");
        result.setDescription("RockMusic");

        mockMvc.perform(post("/genres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(result)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Rock"));
    }

    @Test
    void updateGenre_returnsOk() throws Exception {

        GenreRequestDTO createGenre = new GenreRequestDTO();
        createGenre.setName("Lil Kleine");
        createGenre.setDescription("Hiphop");

        String response = mockMvc.perform(post("/genres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createGenre)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = new ObjectMapper().readTree(response).get("id").asLong();


        GenreRequestDTO result = new GenreRequestDTO();
        result.setName("UPDATED");
        result.setDescription("UPDATED DES");

        mockMvc.perform(put("/genres/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(result)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UPDATED"));
    }


}
