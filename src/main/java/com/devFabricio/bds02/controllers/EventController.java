package com.devFabricio.bds02.controllers;

 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devFabricio.bds02.dto.EventDTO;
import com.devFabricio.bds02.services.EventService;
import com.devFabricio.bds02.services.exceptions.ResourceNotFoundException;


@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	private EventService service;

	@PutMapping("/events/{id}")
	public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@PutMapping("/events/{id}")
	public ResponseEntity<EventDTO> update1(@PathVariable Long id, @RequestBody EventDTO dto) {
		try {
			dto = service.update(id, dto);
			return ResponseEntity.ok().body(dto);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
