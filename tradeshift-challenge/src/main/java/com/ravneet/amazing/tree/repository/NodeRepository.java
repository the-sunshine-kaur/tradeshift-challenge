package com.ravneet.amazing.tree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravneet.amazing.tree.entity.Node;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long>, CustomNodeRepository
{

}
