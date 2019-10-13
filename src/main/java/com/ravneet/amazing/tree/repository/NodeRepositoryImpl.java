package com.ravneet.amazing.tree.repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ravneet.amazing.tree.entity.Node;

public class NodeRepositoryImpl implements CustomNodeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Set<Node> findAllChildren() {
		/**
		 * This will return all children nodes except the root node since root does not
		 * have a parent
		 */
		List<Node> nodeList = entityManager
				.createQuery("SELECT n FROM Node n WHERE n.parentNodeId IS NOT NULL AND n.isRootNode = FALSE;",
						Node.class)
				.getResultList();
		return nodeList.stream().collect(Collectors.toSet());
	}

	@Override
	public Set<Node> findByParentNodeId(Long parentNodeId) {
		List<Node> nodeList = entityManager
				.createQuery("SELECT n FROM Node n WHERE n.parentNodeId = :parentNodeId;", Node.class)
				.setParameter("parentNodeId", parentNodeId + '%').getResultList();
		return nodeList.stream().collect(Collectors.toSet());
	}

}
