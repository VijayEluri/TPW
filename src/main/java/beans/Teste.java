package beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "teste")
@NamedQueries(
	{@NamedQuery(name = "Teste.selectByDescricao", query = "SELECT t FROM Teste t WHERE t.descricao LIKE :descricao")})
public class Teste implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seqTeste", sequenceName = "seq_teste", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTeste")
	private Integer codigo;

	@Column(nullable = false)
	private String descricao;

	private Double valor;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
