package com.lhsys.backenddemo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhsys.backenddemo.controller.PostController;
import com.lhsys.backenddemo.models.entities.Post;
import com.lhsys.backenddemo.repositories.PostRepository;
import com.lhsys.backenddemo.services.PostServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    private MockMvc mock;
    private Post thePost = new Post("dasd", "asdasd", "dasdd");
    private ObjectMapper mapper = new ObjectMapper();
    String json;
    String kaka = "sadasda";

    {
        try {
            json = mapper.writeValueAsString(thePost);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    @Mock
    PostRepository postRepository;

    @Mock
    PostServiceImp postServiceImp;

    @Mock
    Post post;

    @InjectMocks
    PostController postController;

    @Before
    public  void setup()throws Exception{
        MockitoAnnotations.initMocks(this);
        mock =  MockMvcBuilders.standaloneSetup(postController).build();
    }
    //getMapping
    @Test
    public void getAllPost() throws Exception{
        mock.perform(get("/posts")
                .contentType(contentType))
                .andExpect(status().isOk());
    }
    //postMapping
    @Test
    public void postAPost_everythingOk()throws Exception{
       mock.perform(post("/post")
                        .contentType(contentType).content(json)).andExpect(status().isNoContent());
    }
    @Test
    public void postAPost_noInput()throws Exception{
        mock.perform(post("/post")
                .contentType(contentType)).andExpect(status().isBadRequest());
    }

    //deleteApost
    @Test
    public void deleteTest_everythingOk()throws Exception{
        mock.perform(delete("/posts/1")
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest_noPost()throws Exception{
        mock.perform(delete("/posts/1")
                .contentType(contentType))
                .andExpect(status().isOk());
    }


}
