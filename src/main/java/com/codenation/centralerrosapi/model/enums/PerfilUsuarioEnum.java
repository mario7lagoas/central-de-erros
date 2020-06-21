package com.codenation.centralerrosapi.model.enums;

public enum PerfilUsuarioEnum {
	
	DESENVOLVEDOR(0, "Desenvolvedor", "ROLE_DESENVOLVEDOR"),
	ADMINISTRADOR(1, "Administrador", "ROLE_ADMINISTRADOR"),  
	GERENTE(2, "Gerente",  "ROLE_GERENTE"),  
	LOJA(3, "Usuario Loja", "ROLE_LOJA"), 
	CLIENTE(4, "Cliente", "ROLE_CLIENTE"), 
	VENDEDOR(5, "Vendedor", "ROLE_VENDEDOR")
	;

	private int codigo;
	private String descricao;
	private String role;

	private PerfilUsuarioEnum() {
	}

	private PerfilUsuarioEnum(int codigo, String descricao, String role) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.role = role;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static PerfilUsuarioEnum toEnum(Integer codigo) {

		if (codigo == null) {
			return null;
		}
		for (PerfilUsuarioEnum x : PerfilUsuarioEnum.values()) {
			if (codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}
