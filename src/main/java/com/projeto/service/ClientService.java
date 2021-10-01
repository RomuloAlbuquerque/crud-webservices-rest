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
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> readAllPaged(PageRequest pageRequest) {
		Page<Client> page = repository.findAll(pageRequest);
		return page.map(x -> new ClientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO readById(Long id){
		Optional<Client> objOptional = repository.findById(id);
		Client entity = objOptional.get();
		ClientDTO dto = new ClientDTO(entity);
		return dto;
	}
	
	@Transactional
	public ClientDTO create(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		repository.save(entity);
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		Client entity = repository.getOne(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
		
	}
	
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
}
