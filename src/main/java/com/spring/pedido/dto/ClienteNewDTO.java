package com.spring.pedido.dto;

import java.io.Serializable;

public class ClienteNewDTO implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	//Cliente
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
	//Endereco
	private String logradouro;
	private String num;
	private String complemento;
	private String bairro;
	private String cep;
	
	//Telefones
	private String tel1;
	private String tel2;
	private String tel3;
	
	//Cidade
	private Integer cidadeId;
	
	public ClienteNewDTO() {
		
	}

	public ClienteNewDTO(String nome, String email, String cpfOuCnpj, Integer tipo, String logradouro, String num,
			String complemento, String bairro, String cep, String tel1, String tel2, String tel3, Integer cidadeId) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.num = num;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
		this.cidadeId = cidadeId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
}
