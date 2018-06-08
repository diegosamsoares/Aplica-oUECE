package poo;

public enum TipoFalta {
	ATESTADO("Atestado M�dico"), PONTOELETRONICO("Problema No Ponto Eletr�nico"), SERVICO("A servi�o"), LICENCA(
			"Licen�a"), OUTROS("Outros");

	private String descricao;

	TipoFalta(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public String toString() {
		return this.descricao;
	}

}
