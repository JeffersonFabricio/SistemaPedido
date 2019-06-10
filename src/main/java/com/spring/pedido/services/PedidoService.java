package com.spring.pedido.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.pedido.domain.Pedido;
import com.spring.pedido.repositories.PedidoRepository;
import com.spring.pedido.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository rep;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = rep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
