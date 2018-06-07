package poo;

import entities.annotations.EntityDescriptor;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NovaSolicitacao")
@EntityDescriptor(hidden = true)
public class NovaSolicitacao extends Status {

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
        
        solicitacao.setStatus(new AguardandoChefia());
        
        
	
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
	throw new IllegalStateException("Solicitação não pode ser retornada.");
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