package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "minicurso")
@SequenceGenerator(name = "minicurso_id_seq", sequenceName = "minicurso_id_seq")   
public class Minicurso {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="minicurso_id_seq")
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column
	private String data;

	@Column
	private String descricao;

	@Column
	private String responsavel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

}
