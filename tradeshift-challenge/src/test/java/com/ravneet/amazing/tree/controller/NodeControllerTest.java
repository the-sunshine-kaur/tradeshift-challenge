package com.ravneet.amazing.tree.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@PropertySource(value = {"classpath:application.properties"})
@Transactional
public class NodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setup(){
        entityManager.createQuery("DELETE FROM node").executeUpdate();
        entityManager.createQuery("INSERT INTO node (id, parentNodeId, isRoot, nodeHeight) VALUES (1L, 0L, false, 0)").executeUpdate();
        entityManager.createQuery("INSERT INTO node (id, parentNodeId, isRoot, nodeHeight) VALUES (2L, 1L, false, 1)").executeUpdate();
        entityManager.createQuery("INSERT INTO node (id, parentNodeId, isRoot, nodeHeight) VALUES (3L, 1L, false, 1)").executeUpdate();
        entityManager.createQuery("INSERT INTO node (id, parentNodeId, isRoot, nodeHeight) VALUES (4L, 2L, false, 2)").executeUpdate();
        entityManager.createQuery("INSERT INTO node (id, parentNodeId, isRoot, nodeHeight) VALUES (5L, 3L, false, 2)").executeUpdate();
        entityManager.createQuery("INSERT INTO node (id, parentNodeId, isRoot, nodeHeight) VALUES (6L, 4L, false, 4)").executeUpdate();
        entityManager.createQuery("INSERT INTO node (id, parentNodeId, isRoot, nodeHeight) VALUES (7L, 4L, false, 4)").executeUpdate();
        entityManager.createQuery("INSERT INTO node (id, parentNodeId, isRoot, nodeHeight) VALUES (8L, 5L, false, 4)").executeUpdate();
    }


    @After
    public void clear(){
        entityManager.createQuery("DELETE FROM node").executeUpdate();
    }



    @Test
    public void testUpdateNode() throws Exception{

        mockMvc.perform(put("/node/updatenode/8L")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"parentNodeId\":\"6L\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseObject.parentNodeId").value(6L))
                .andExpect(jsonPath("$.responseObject.isRoot").value(false));
    }

    @Test
    public void testFindNodeById() throws Exception{

        mockMvc.perform(get("/node/get/11"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseObject.id").value(11))
                .andExpect(jsonPath("$.responseObject.parentNodeId").value("B"))
                .andExpect(jsonPath("$.responseObject.isRoot").value(false))
                .andExpect(jsonPath("$.responseObject.nodeHeight").value(11));
    }

    @Test
    public void testGetChildren() throws Exception{

        mockMvc.perform(get("/node/children/10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseObject", hasSize(5)));
    }

    @Test
    public void testGetAllChildren() throws Exception{

        mockMvc.perform(get("/node/allchildren/10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseObject", hasSize(7)));
    }

    @Test
    public void testChangeParent() throws Exception{

        mockMvc.perform(get("/node/changeparent/11/12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andReturn();

        mockMvc.perform(get("/node/get/14"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseObject.path").value("10,12,11,"));
    }
}
