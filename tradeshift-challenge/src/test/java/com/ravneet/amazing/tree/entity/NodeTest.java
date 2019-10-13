package com.ravneet.amazing.tree.entity;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.ravneet.amazing.tree.repository.NodeRepository;

public class NodeTest {

    @Test
    public void testGetId(){
        Assert.assertEquals(new Long(1), new Node( 1L, 2L, false, 10).getId());;
    }
    
    @Test
    public void testGetParentNodeId(){
        Assert.assertEquals(new Long(2) , new Node( 1L, 2L, false, 10).getParentNodeId());
    }
	
    @Test
    public void testGetHeight(){
        Assert.assertEquals(10 , new Node( 1L, 2L, false, 10).getNodeHeight());
    }
    @Bean
	public CommandLineRunner commandlineRunner(NodeRepository nodeRepository) throws Exception {
	
	  return args -> {
		  nodeRepository.deleteAllInBatch();
	    ArrayList<Node> nodes = new ArrayList<>();
	    nodes.add(new Node(1L, 0L, true, 0));
	    nodes.add(new Node(2L, 1L, false, 1));
	    nodes.add(new Node(4L, 1L, false, 1));
	    nodes.add(new Node(5L, 2L, false, 2));
	    nodes.add(new Node(6L, 4L, false, 2));
	    nodeRepository.saveAll(nodes);
	  };
	}
}

