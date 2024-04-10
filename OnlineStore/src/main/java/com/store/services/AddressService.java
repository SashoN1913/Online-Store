package com.store.services;

import com.store.models.Address;
import com.store.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService
{
	@Autowired
	private AddressRepository addressRepository;

	public List<Address> getAddresses()
	{
		return addressRepository.findAll();
	}

	public Address getAddress(Long id)
	{
		Optional<Address> p = addressRepository.findById(id);
		return p.get();
	}

	public void addAddress(Address p)
	{
		addressRepository.save(p);
	}

	public void removeAddress(Long id)
	{
		addressRepository.deleteById(id);
	}

	public void updateAddress(Long id, Address address)
	{
		address.setId(id);
		addAddress(address);
	}
}
