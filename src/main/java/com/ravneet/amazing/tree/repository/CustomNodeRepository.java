package com.ravneet.amazing.tree.repository;

import java.util.Set;

import com.ravneet.amazing.tree.entity.Node;

public interface CustomNodeRepository {
	Set<Node> findAllChildren();
	Set<Node> findByParentNodeId(Long parentNodeId);
}
