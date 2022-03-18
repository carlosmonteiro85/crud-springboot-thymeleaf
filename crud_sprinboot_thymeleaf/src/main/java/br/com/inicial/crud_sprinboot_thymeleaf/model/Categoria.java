package br.com.inicial.crud_sprinboot_thymeleaf.model;

public enum Categoria {

	ADM(0, "Administrador"), DEFAULT(1, "Padrão");

	private Integer codigo;
	private String name;

	private Categoria(Integer codigo, String name) {
		this.codigo = codigo;
		this.name = name;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNome() {
		return name;
	}

	public static Categoria obterPorCodigo(Integer codigo) {
		for (Categoria categoria : values()) {
			if (categoria.getCodigo().equals(codigo)) {
				return categoria;
			}
		}
		return null;
	}

	public static Categoria getForName(String name) {
		for (Categoria categoria : values()) {
			if (categoria.getNome().equals(name)) {
				return categoria;
			}
		}
		return null;
	}

}