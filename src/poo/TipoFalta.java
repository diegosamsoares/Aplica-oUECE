package poo;

public enum TipoFalta {
	ATESTADO("Atestado Médico"), PONTOELETRONICO("Problema No Ponto Eletrônico"), SERVICO("A serviço"), LICENCA(
			"Licença"), OUTROS("Outros");

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
