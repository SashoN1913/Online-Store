package com.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>
{
}