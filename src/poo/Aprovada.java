package poo;

import entities.annotations.EntityDescriptor;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Aprovada")
@EntityDescriptor(hidden = true)
public class Aprovada extends Status {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Aprovada() {
        this.setId(4);
        this.setDescricao("Aprovada");
    }
    
    Aprovada(Solicitacao solicitacao) {
        this();
        this.solicitacao = solicitacao;
    }
    
    @Override
    public void solicitar() {
	throw new IllegalStateException("Solicitação não pode ser solicitada.");
    }
    
    @Override
    public void aprovar() {
	throw new IllegalStateException("Solicitação não pode ser aprovada.");
    }
    
    @Override
    public void recusar() {
	throw new IllegalStateException("Solicitação não pode ser recusada.");
    }
    
    @Override
    public void retornar(String motivo) {
	        if (motivo == null) {
         //   throw new IllegalArgumentException("Motivo inválido");
         solicitacao.setMotivo("");
        }
        solicitacao.setMotivo(motivo);
        solicitacao.setStatus(new AguardandoRH());
    }
    
}