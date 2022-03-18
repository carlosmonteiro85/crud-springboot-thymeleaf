package br.com.inicial.crud_sprinboot_thymeleaf.model;



public enum Roles {
	
	DEFALT(1), ADMINISTRADOR(0);

	private Integer codigo;
	
	private Roles(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public static Roles obterPorCodigo(Integer codigo) {
		for (Roles categoria : values()) {
			if (categoria.getCodigo().equals(codigo)) {
				return categoria;
			}
		}
		return null;
	}
	
}