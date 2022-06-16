package com.example.backend.post;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.backend.config.security.SecurityProperties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

/**
 * PostGraphqlControllerTest
 *
 * @author JuliusR
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@EnableConfigurationProperties(SecurityProperties.class)
public class PostSampleTests {

    @LocalServerPort
    private int port;

    @Autowired
    public HttpGraphQlTester graphQlTester;

    @Autowired
    public SecurityProperties properties;

    @BeforeAll
    public void setUp() {
        this.graphQlTester = graphQlTester.mutate()
                .headers(headers -> headers.setBasicAuth(properties.getLogin(), properties.getPassword()))
                .build();
    }

    @Test
    void canQueryPosts() {
        graphQlTester.documentName("posts")
                .execute()
                .path("posts[0].id").entity(Long.class).isEqualTo(1l)
                .path("posts[0].userId").entity(Long.class).isEqualTo(1l);
    }

    @Test
    void canCreatePost() {
        graphQlTester.documentName("createPost")
                .execute()
                .path("createPost.userId").entity(Long.class).isEqualTo(1l)
                .path("createPost.id").entity(Long.class).isEqualTo(101l)
                .path("createPost.title").entity(String.class).isEqualTo("A nice title")
                .path("createPost.body").entity(String.class).isEqualTo("Nice body");
    }

    @Test
    void noCredentials() {
        assertThatThrownBy(() -> this.graphQlTester.mutate()
                .headers(headers -> headers.clear())
                .build()
                .documentName("posts")
                .executeAndVerify())
                        .hasMessage("Status expected:<200 OK> but was:<401 UNAUTHORIZED>");
    }

    @Test
    void invalidCredentials() {
        assertThatThrownBy(() -> this.graphQlTester.mutate()
                .headers(headers -> headers.setBasicAuth("admin", "letmein"))
                .build()
                .documentName("posts")
                .executeAndVerify())
                        .hasMessage("Status expected:<200 OK> but was:<401 UNAUTHORIZED>");
    }

}
