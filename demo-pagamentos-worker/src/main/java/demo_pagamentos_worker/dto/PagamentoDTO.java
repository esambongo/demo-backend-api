package demo_pagamentos_worker.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PagamentoDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5595869191036995584L;
	private String numeroPedido;
	private BigDecimal valor;
	private String produto;
	public String getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	@Override
	public String toString() {
		return "PagamentoDTO [numeroPedido=" + numeroPedido + ", valor=" + valor + ", produto=" + produto + "]";
	}
	
	
	
}
