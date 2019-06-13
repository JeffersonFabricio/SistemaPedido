package com.spring.pedido.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.pedido.domain.Cidade;
import com.spring.pedido.domain.Cliente;
import com.spring.pedido.domain.Endereco;
import com.spring.pedido.domain.enums.TipoCliente;
import com.spring.pedido.dto.ClienteDTO;
import com.spring.pedido.dto.ClienteNewDTO;
import com.spring.pedido.repositories.ClienteRepository;
import com.spring.pedido.repositories.EnderecoRepository;
import com.spring.pedido.services.exceptions.DataIntegrityException;
import com.spring.pedido.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository ClienteRep;

	@Autowired
	private EnderecoRepository enderecoRep;
	
	public List<Cliente> findAll() {
		return ClienteRep.findAll();
	}

	public Cliente find(Integer id) {
		Optional<Cliente> obj = ClienteRep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		ClienteRep.save(obj);
		enderecoRep.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return ClienteRep.save(newObj);
	}

	public void delete(Integer id) {
		find(id);

		try {
			ClienteRep.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir Cliente que possuí pedidos.");
		}

	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return ClienteRep.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNum(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTel1());
		
		if (objDto.getTel2()!= null)
			cli.getTelefones().add(objDto.getTel2());
		
		if (objDto.getTel3()!= null) 
			cli.getTelefones().add(objDto.getTel3());
		
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
