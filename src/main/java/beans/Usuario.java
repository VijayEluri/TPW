package beans;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author vendra
 * Classe responsável pelo cadastro de Usuarios
 *
 */
@Entity
@Table(name = "usuario")
public class Usuario {
	
	/**
	 * id do usuario
	 */
	@Id
	private String login;

	/**
	 * senha do usuario
	 */
	@Column(nullable = false)
	private String password;

	/**
	 * nome do usuario
	 */
	@Column(nullable = false)
	private String nome;

	/**
	 * email do usuario
	 */
	private String email;

	/**
	 * tipo do usuario
	 */
	@Column(nullable = false, name="tipo")
	@Enumerated(value=EnumType.STRING)
	private String tipoUsuario;
	
	/**
	 * Blogs postados pelo usuario
	 */
	@OneToMany (mappedBy="usuario", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Post> posts;

	/**
	 * Eventos que o usuario esta inscrito
	 */
	@ManyToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
		    mappedBy = "usuarios",
		    targetEntity = Evento.class
	)	
	private Set<Evento> eventos;

	/**
	 * Minicursos que o usuario esta inscrito
	 */
	@ManyToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
		    mappedBy = "usuarios",
		    targetEntity = Minicurso.class
	)
	private Set<Minicurso> minicursos;
	
	/*
	 * ============================
	 * Setters e Getters
	 * ============================
	 */
	public Set<Minicurso> getMinicursos(){
		return minicursos;
	}
	
	public Set<Evento> getEventos() {
		return eventos;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
}
