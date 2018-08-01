package com.lhsys.backenddemo.service;

import com.lhsys.backenddemo.models.entities.Post;
import com.lhsys.backenddemo.repositories.PostRepository;
import com.lhsys.backenddemo.services.PostServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {
    private MockMvc mock;
    String title = "test";
    String url = "http://test.hu";
    String reddit = "tung123";

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImp postServiceImp;

    private List<Post> generateTestPost(int num){
       List<Post> testPosts = new ArrayList<>();
        for (int i = 0; i <num ; i++) {
            testPosts.add(new Post("Hello", "google.com", String.valueOf(i)));
        }
        return testPosts;
    }

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock() {
        assertNotNull(postRepository);
        assertNotNull(postServiceImp);
    }

    @Test
    public void submitPostTest() {

        Post post = new Post(title, url, reddit);
        Mockito.verify(postRepository,Mockito.atLeastOnce()).save(Mockito.any(Post.class));
        postServiceImp.postSave(post);

    }




}
