package beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "evento")   
public class Evento {
	
	@Id
	@SequenceGenerator(name = "evento_id_seq", sequenceName = "evento_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="evento_id_seq")
	private Integer id;

	@Column(nullable = false)
	private String nome;

	@Column
	private Date data;

	@Column
	private String descricao;

	@Column
	private String responsavel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
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
