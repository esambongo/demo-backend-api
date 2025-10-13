package br.com.fiap.minfin.CamelDemo.model;

import java.io.Serializable;

public record Order(String id, String type,double amout,String description, String customer) implements Serializable{}
