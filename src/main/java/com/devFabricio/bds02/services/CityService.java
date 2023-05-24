package com.devFabricio.bds02.services;


import java.util.List;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devFabricio.bds02.dto.CityDTO;
import com.devFabricio.bds02.entities.City;
import com.devFabricio.bds02.repositoryes.CityRepository;
import com.devFabricio.bds02.services.exceptions.DatabaseException;
import com.devFabricio.bds02.services.exceptions.ResourceNotFoundException;



@Service
public class CityService {

	@Autowired
	private CityRepository repository;

	@Transactional
	public List<CityDTO> findAll() {
		List<City> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public CityDTO insert(CityDTO dto) {
		City entity = new City(null, dto.getName());
		entity = repository.save(entity);
		return new CityDTO(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	public void delete1(Long id)  {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}
	}

	@Transactional
	public void delete2(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
