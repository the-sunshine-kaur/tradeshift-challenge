package com.ravneet.amazing.tree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ravneet.amazing.tree.entity.Node;
import com.ravneet.amazing.tree.exception.ServiceException;
import com.ravneet.amazing.tree.exception.ServiceException.ErrorCode;
import com.ravneet.amazing.tree.repository.NodeRepository;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/node")
public class NodeController {

	@Autowired
	private NodeRepository nodeRepository;

	@PostMapping("/create")
	public Node create(@RequestBody Node node) {
		return nodeRepository.save(node);
	}

	@GetMapping("/findall")
	public List<Node> findAll() {
		return nodeRepository.findAll();
	}

	@PutMapping("/updatenode/{id}")
	public Node updateNode(@PathVariable("id") Long nodeId, @RequestBody Node nodeObject) {
		Node node = nodeRepository.findById(nodeId).orElseThrow(() -> new ServiceException(ErrorCode.NODE_NOT_FOUND));
		node.setNodeHeight(nodeObject.getNodeHeight());
		node.setParentNodeId(nodeObject.getParentNodeId());
		node.setRootNode(nodeObject.isRootNode());
		return nodeRepository.save(node);
	}

	/*
	 * @DeleteMapping("/delete/{id}") public List<Node> delete(@PathVariable("id")
	 * Long nodeId) { nodeRepository.findById(nodeId).orElseThrow(() -> new
	 * ServiceException(ErrorCode.NODE_NOT_FOUND)); nodeRepository.delete(nodeId);
	 * return nodeRepository.findAll(); }
	 */

	@GetMapping("/get/{id}")
	@ResponseBody
	public Node findByNodeId(@PathVariable("id") Long nodeId) {
		return nodeRepository.findById(nodeId).orElseThrow(() -> new ServiceException(ErrorCode.NODE_NOT_FOUND));
	}

	@GetMapping("/get/{parentNodeId}")
	public ResponseEntity<Set<Node>> findByParentNodeId(@PathVariable("parentNodeId") Long parentNodeId) {
		nodeRepository.findById(parentNodeId).orElseThrow(() -> new ServiceException(ErrorCode.PARENT_NOT_FOUND));
		return ResponseEntity.ok(nodeRepository.findByParentNodeId(parentNodeId));
	}

	@GetMapping("/findallchildren")
	public ResponseEntity<Set<Node>> findAllChildren() {
		return ResponseEntity.ok(nodeRepository.findAllChildren());
	}

	@PutMapping("/changeParent/{id}/{parentNodeId}")
	public Node changeParent(@PathVariable("id") Long nodeId, @PathVariable("parentNodeId") Long parentNodeId) {
		Node node = nodeRepository.findById(nodeId).orElseThrow(() -> new ServiceException(ErrorCode.NODE_NOT_FOUND));
		if (!node.isRootNode()) {
			throw new ServiceException(ErrorCode.PARENT_CHANGE_ILLEGAL);
		}
		nodeRepository.findById(parentNodeId).orElseThrow(() -> new ServiceException(ErrorCode.PARENT_NOT_FOUND));

		node.setParentNodeId(parentNodeId);
		return nodeRepository.save(node);
	}

}
