package br.com.inicial.crud_sprinboot_thymeleaf.model;



public enum Categoria {
	
	USUARIO(1), ADMINISTRADOR(0);

	private Integer codigo;
	
	private Categoria(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public static Categoria obterPorCodigo(Integer codigo) {
		for (Categoria categoria : values()) {
			if (categoria.getCodigo().equals(codigo)) {
				return categoria;
			}
		}
		return null;
	}
	
}