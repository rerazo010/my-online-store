package com.mystore.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mystore.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Serializable> {

	
	@Query("SELECT o FROM Order o, User u WHERE o.userId = u.id and  u.username = :username")
	public Page<Order> findByUserName(String username, Pageable pageable);

		

	

}
