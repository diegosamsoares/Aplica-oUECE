package poo;

import entities.annotations.EntityDescriptor;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("AguardandoChefia")
@EntityDescriptor(hidden = true)
public class AguardandoChefia extends Status {

    public AguardandoChefia() {
        this.setId(2);
        this.setDescricao("AguardandoChefia");
    }
    
    AguardandoChefia(Solicitacao solicitacao) {
        this();
        this.solicitacao = solicitacao;
    }
    
    @Override
    public void solicitar() {
	throw new IllegalStateException("Solicitação já criada.");
    }
    
    @Override
    public void aprovar() {
	AguardandoRH aguardandoRH = new AguardandoRH(solicitacao);
        solicitacao.setStatus(aguardandoRH);
    }
    
    @Override
    public void recusar() {
	Recusada recusada = new Recusada(solicitacao);
        solicitacao.setStatus(recusada);
    }
    
    @Override
    public void retornar(String motivo) {
	throw new IllegalStateException("Solicitação não pode ser retornada.");
    }
}