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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.NamedQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import daos.UsuarioDAO;

/**
 * 
 * @author vendra
 * @see Evento
 * Classe responsável pelo cadastro de Minicursos
 *
 */
@Entity
@Table(name = "minicurso")
@NamedQuery(name="minicurso.last", query="SELECT m FROM Minicurso m ORDER BY m.data DESC limit 3")
public class Minicurso {

	/**
	 * id do minicurso
	 */
	@Id
	@SequenceGenerator(name = "minicurso_id_seq", sequenceName = "minicurso_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="minicurso_id_seq")
	private Integer id;

	/**
	 * Quantidade de Vagas totais para o Minicurso
	 */
	@Column
	private Integer qtVagas;
	
	/**
	 * Numero de inscritos para o minicurso
	 */
	@Column
	private Integer qtInscritos;
	
	/**
	 * Nome do minicurso
	 */
	@Column(nullable = false)
	private String nome;

	/**
	 * Data que ocorrerá o minicurso
	 */
	@Column
	private Date data;

	/**
	 * Descricao detalhada sobre o minicurso
	 */
	@Column
	private String descricao;

	/**
	 * Responsável que irá ministrar o minicurso
	 */
	@Column
	private String responsavel;

	/**
	 * Lista de usuários inscritos no minicurso
	 */
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

	/*
	 * ====================================
	 * Setters e Getters
	 * ====================================
	 */
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

	public Integer getQtVagas() {
		return qtVagas;
	}

	public void setQtVagas(Integer qtVagas) {
		this.qtVagas = qtVagas;
	}
	
	public Integer getQtInscritos() {
		return qtInscritos;
	}

	public void setQtInscritos(Integer qtInscritos) {
		this.qtInscritos = qtInscritos;
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

	/**
	 * Incrementa o contador de inscritos no minicurso
	 */
	public void addQtInscrito(){
		if (qtInscritos==null)
			qtInscritos = new Integer(0);
		qtInscritos++;
	}
	
	/**
	 * Decrementa o contador de inscritos no minicurso
	 */
	public void delQtInscrito(){
		if (qtInscritos==null)
			qtInscritos = new Integer(0);
		else
			qtInscritos--;
	}
	
	/**
	 * Método reponsável por verificar se o usuário está inscrito no minicurso
	 * Usado no listMinicursos.jsp para mostrar as opcoes de se inscrever ou sair
	 * @return Se o usuário logado (na sessão) está inscrito no minicurso
	 */
	public boolean getUsuarioInscrito(){
		
		//Usuario
		Usuario u;
		
		//Recebe o usuario logado
		HttpServletRequest request;
		HttpSession session;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		String tmpLogin = (String) session.getAttribute("login");
		
		//Lendo as informacoes do usuario logado no banco
		ApplicationContext ctxUsuario;
		UsuarioDAO daoUsuario;
		ctxUsuario = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		daoUsuario = (UsuarioDAO) ctxUsuario.getBean("usuarioDAO");
		u=daoUsuario.selectByLogin(tmpLogin);
		
		//Verifica se o usuário está logado e se está contido na lista de usuários inscritos
		if (u!=null){
			if (this.getUsuarios()!=null){
				return this.getUsuarios().contains(u);
			}
		}
		return false;
	}
	
}
