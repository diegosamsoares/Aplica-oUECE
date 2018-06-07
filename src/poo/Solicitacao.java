package poo;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import entities.Repository;
import entities.annotations.ActionDescriptor;
import entities.annotations.Editor;
import entities.annotations.ParameterDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import entities.descriptor.PropertyType;

@NamedQueries({
		@NamedQuery(name = "SolicitacoesAguardandoChefia", query = "Select s "
				+ " From Solicitacao s, AguardandoChefia st" + " Where s.status = st"),
		@NamedQuery(name = "SolicitacoesAguardandoRH", query = "Select s " + " From Solicitacao s, AguardandoRH st"
				+ " Where s.status = st"),

		@NamedQuery(name = "SolicitacaoesAprovada", query = "Select s  From Solicitacao s, Aprovada a Where s.status = a")

})

@Views({ @View(name = "SolicitarAbono", title = "Solicitar Abono", namedQuery = "Select new poo.Solicitacao()", members = "[#funcionario;#inicio; "
		+ " #termino;#motivo;#observacao;#tipoFalta; " + " #solicitar()]"),

		@View(name = "AprovacaoDaChefia", title = "Analisar Solicitações", namedQuery = "SolicitacoesAguardandoChefia", members = "[*funcionario:2;"
				+ " *inicio,*termino;*motivo,*observacao]," + " [aprovar();recusar()]"),

		@View(name = "AbonarFaltas", title = "Abonar Faltas", namedQuery = "SolicitacoesAguardandoRH", members = "[*funcionario;*inicio;*termino],"
				+ "*motivo," + "[aprovar();recusar();retornar()]")

})

@Entity
public class Solicitacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6856268576468483149L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "funcionario")
	Funcionario funcionario;

	@Temporal(TemporalType.DATE)
	@Column(name = "inicio")
	Date inicio;

	@Column(name = "motivo", length = 60, nullable = false)
	String motivo;

	@Column(name = "observacao", length = 60, nullable = false)
	String observacao;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	Status status = new NovaSolicitacao();

	@Temporal(TemporalType.DATE)
	@Column(name = "termino")
	Date termino;

	@Column(name = "VERSION", length = 20)
	private Timestamp version;
	
	@NotNull(message = "Informe o Tipo da Falta")
    @Enumerated(EnumType.STRING)
    private TipoFalta tipoFalta;

	@ActionDescriptor(refreshView = true)
	public void aprovar() {
		status.setSolicitacao(this).aprovar();
		Repository.getInstance().add(this);
		Repository.getInstance().persistAll();
	}

	@ActionDescriptor(confirm = true, refreshView = true)
	public void recusar() {
		status.setSolicitacao(this).recusar();
		Repository.getInstance().add(this);
		Repository.getInstance().persistAll();
	}

	@ActionDescriptor(confirm = true, refreshView = true)
	public void retornar(
			@Editor(propertyType = PropertyType.MEMO) @ParameterDescriptor(displayName = "Motivo") String motivo) {

		// status = new AguardandoRH();
		status.setSolicitacao(this).retornar(motivo);
		Repository.getInstance().add(this);
		Repository.getInstance().persistAll();
	}

	@ActionDescriptor(refreshView = true)
	public String solicitar() {
		status = new NovaSolicitacao();
		status.setSolicitacao(this).solicitar(); // status->solicitar()
		Repository.getInstance().add(this);
		Repository.getInstance().persistAll();
		return "Solicitação adicionada com sucesso!!!";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public Timestamp getVersion() {
		return version;
	}

	public void setVersion(Timestamp version) {
		this.version = version;
	}

	public TipoFalta getTipoFalta() {
		return tipoFalta;
	}

	public void setTipoFalta(TipoFalta tipoFalta) {
		this.tipoFalta = tipoFalta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((termino == null) ? 0 : termino.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solicitacao other = (Solicitacao) obj;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		if (motivo == null) {
			if (other.motivo != null)
				return false;
		} else if (!motivo.equals(other.motivo))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (termino == null) {
			if (other.termino != null)
				return false;
		} else if (!termino.equals(other.termino))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}