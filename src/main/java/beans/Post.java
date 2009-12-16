package beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

/**
 * 
 * @author tjcampos
 * Classe responsável pelo cadastro de Blogs
 *
 */
@Entity
@Table(name = "post")
@NamedQuery(name="post.lastPosts", query="SELECT p FROM Post p ORDER BY p.id DESC")
public class Post {
	
	/**
	 * Id do Post
	 */
	@Id
    @SequenceGenerator(name="PostSeq",sequenceName="seq_post_id", allocationSize=1)
    @GeneratedValue(generator="PostSeq", strategy = GenerationType.SEQUENCE)
    private Integer id;
	
	/**
	 * Titulo do post
	 */
	@Column
	private String titulo;
	
	/**
	 * Data do post
	 */
	@Column
	private Date data;
	
	/**
	 * Texto (Conteudo) do post
	 */
	@Column
	private String texto;

	/**
	 * Usuario que criou este post
	 */
    //@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE} )
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="login", referencedColumnName="login")
	private Usuario usuario;
    
	/**
	 * Construtor
	 * Seta a data do post como a data atual
	 */
    public Post(){
    	data = new Date();
    }

    /*
     * =======================
     * Setters e Getters
     * =======================
     */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
