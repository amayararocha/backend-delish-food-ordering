package com.delish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delish.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
