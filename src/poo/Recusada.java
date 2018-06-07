package poo;

import entities.annotations.EntityDescriptor;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Recusada")
@EntityDescriptor(hidden = true)
public class Recusada extends Status {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2850435053336195352L;

	public Recusada() {
        this.setId(5);
        this.setDescricao("Recusada");
    }

    Recusada(Solicitacao solicitacao) {
        this();
        this.solicitacao = solicitacao;
    }

    @Override
    public void solicitar() {
        throw new IllegalStateException("Esta solicita��o n�o pode ser reiniciada.");
    }
    
    @Override
    public void aprovar() {
        throw new IllegalStateException("Esta solicita��o n�o pode ser aprovada.");
    }
    
    @Override
    public void recusar() {
        throw new IllegalStateException("Esta solicita��o n�o pode ser recusada.");
    }
    
    @Override
    public void retornar(String motivo) {
        throw new IllegalStateException("Esta solicita��o n�o pode ser retornada.");
    }
}