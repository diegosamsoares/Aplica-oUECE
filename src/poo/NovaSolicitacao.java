package poo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import entities.Repository;
import entities.annotations.EntityDescriptor;

@Entity
@DiscriminatorValue("NovaSolicitacao")
@EntityDescriptor(hidden = true)
public class NovaSolicitacao extends Status {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202689391235071688L;
	
	

	public NovaSolicitacao() {
		this.setId(1);
		this.setDescricao("NovaSolicitacao");
	}

	NovaSolicitacao(Solicitacao solicitacao) {
		this();
		this.solicitacao = solicitacao;
	}

	@Override
	public void solicitar() {
		if (solicitacao.getTipoFalta().getDescricao().equalsIgnoreCase("Atestado Medico")|| solicitacao.getTipoFalta().getDescricao().equalsIgnoreCase("Problema No Ponto Eletronico")) {
            solicitacao.setStatus(new AguardandoRH());
            Repository.getInstance().add(this);
            Repository.getInstance().persistAll();
        }else {
        	solicitacao.setStatus(new AguardandoChefia());
        }

	}

	@Override
	public void aprovar() {
		throw new IllegalStateException("Solicita��o n�o pode ser aprovada.");
	}

	@Override
	public void recusar() {
		throw new IllegalStateException("Solicita��o n�o pode ser recusada.");
	}

	@Override
	public void retornar(String motivo) {
		throw new IllegalStateException("Solicita��o n�o pode ser retornada.");
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	


}