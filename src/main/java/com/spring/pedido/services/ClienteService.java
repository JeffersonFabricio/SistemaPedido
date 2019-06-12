package com.spring.pedido.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.spring.pedido.domain.Cliente;
import com.spring.pedido.dto.ClienteDTO;
import com.spring.pedido.repositories.ClienteRepository;
import com.spring.pedido.services.exceptions.DataIntegrityException;
import com.spring.pedido.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository rep;

	public List<Cliente> findAll() {
		return rep.findAll();
	}

	public Cliente find(Integer id) {
		Optional<Cliente> obj = rep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return rep.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return rep.save(newObj);
	}

	public void delete(Integer id) {
		find(id);

		try {
			rep.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir Cliente que possuí entidades relacionadas.");
		}

	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return rep.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
