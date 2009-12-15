package beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "minicurso")   
public class Minicurso {

	@Id
	@SequenceGenerator(name = "minicurso_id_seq", sequenceName = "minicurso_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="minicurso_id_seq")
	private Integer id;

	@Column(nullable = false)
	private String nome;

	@Column
	private Date data;

	@Column
	private String descricao;

	@Column
	private String responsavel;

	@ManyToMany(
		cascade={CascadeType.PERSIST, CascadeType.MERGE},
		targetEntity = Usuario.class
	)
	@JoinTable(
		name="Minicurso_Usuario",
		joinColumns=@JoinColumn(name="id"),
		inverseJoinColumns=@JoinColumn(name="login")
	)
	private Set<Usuario> usuarios;

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
		
	public void setUsuario(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
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
