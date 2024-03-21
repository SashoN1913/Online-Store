package com.store.models;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
@Table(name="user_order")
public class Order
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String status;
	private Date date;
	private Double total;
	
	private int userId;
	
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Address address;

	public Order()
	{

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Double getTotal()
	{
		return total;
	}

	public void setTotal(Double total)
	{
		this.total = total;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	@Override
	public String toString()
	{
		return "Order [id=" + id + ", status=" + status + ", date=" + date + ", total=" + total + ", userId=" + userId
				+ ", address=" + address + "]";
	}
}
