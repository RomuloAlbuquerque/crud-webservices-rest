package com.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.dto.ClientDTO;
import com.projeto.entity.Client;
import com.projeto.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository repository;
	
	@Transactional
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> page = repository.findAll(pageRequest);
		return page.map(x -> new ClientDTO(x));
	}
	
	@Transactional
	public ClientDTO findById(Long id){
		Optional<Client> objOptional = repository.findById(id);
		Client entity = objOptional.get();
		ClientDTO dto = new ClientDTO(entity);
		return dto;
	}
}
