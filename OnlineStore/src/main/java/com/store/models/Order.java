package com.store.models;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_order")
public class Order
{
	@Id
	private int id;
	private String status;
	private Date date;
	private Double total;
	private int userId;
	private int addressId;

	public Order()
	{

	}

	public Order(int id, String status, Date date, Double total, int userId, int addressId)
	{
		super();
		this.id = id;
		this.status = status;
		this.date = date;
		this.total = total;
		this.userId = userId;
		this.addressId = addressId;
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

	public int getAddressId()
	{
		return addressId;
	}

	public void setAddressId(int addressId)
	{
		this.addressId = addressId;
	}
	
	@Override
	public String toString()
	{
		return "Order [id=" + id + ", status=" + status + ", date=" + date + ", total=" + total + ", userId=" + userId
				+ ", addressId=" + addressId + "]";
	}
}
