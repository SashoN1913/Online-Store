package com.store.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String label;
	private String country;
	private String city;
	private String street;
	private String zip;
	private int userId;
	
	public Address()
	{
		
	}

	public Address(int id, String label, String country, String city, String street, String zip, int userId)
	{
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.street = street;
		this.zip = zip;
		this.userId = userId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	@Override
	public String toString()
	{
		return "Address{" + "id=" + id + ", label='" + label + '\'' + ", country='" + country + '\'' + ", city='" + city
				+ '\'' + ", street='" + street + '\'' + ", zip='" + zip + '\'' + ", userId=" + userId + '}';
	}
}
