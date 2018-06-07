package poo;

import entities.annotations.EntityDescriptor;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@EntityDescriptor(hidden = true)
public abstract class Status implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4508664835860016182L;

	@Id
    private Integer id;

    @Column(length = 40)
    private String descricao;

    @Transient
    Solicitacao solicitacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
        return this;
    }

    public abstract void solicitar();

    public abstract void aprovar();

    public abstract void recusar();

    public abstract void retornar(String motivo);
    
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Status other = (Status) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 11 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        //return this.id == null ? "<Novo>" : descricao;
        return descricao;
    }
    
}
