package com.spring.pedido.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.spring.pedido.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
	public static final long serialVersionUID = 1L;
	
	private Integer numParcelas;
	
	public PagamentoComCartao() {}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numParcelas) {
		super(id, estado, pedido);
		this.numParcelas = numParcelas;
	}

	public Integer getNumParcelas() {
		return numParcelas;
	}

	public void setNumParcelas(Integer numParcelas) {
		this.numParcelas = numParcelas;
	}

}
