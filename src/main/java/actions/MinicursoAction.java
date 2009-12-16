package actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Minicurso;
import beans.Usuario;

import com.opensymphony.xwork2.ActionSupport;

import daos.MinicursoDAO;
import daos.UsuarioDAO;

/**
 * Classe responsável por cuidar das acoes do Minicurso.
 * Geralmente chamadas pelo struts. 
 * @author vendra
 *
 */
public class MinicursoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * Minicurso associado a esta acao
	 */
	private Minicurso minicurso;
	
	/**
	 * Lista de minicursos
	 */
	private List<Minicurso> minicursos;

	// Lista de inscritos no minicurso
	private List<Usuario> inscritos;

	private String strData;
	
	//Dao para conexao com o banco
	private MinicursoDAO dao;

	//Spring
	private ApplicationContext ctx;

	// Confirmação da password
	private String confirmacao;
	
	/**
	 * Construtor: Inicializa o minicurso, dao e ctx
	 */
	public MinicursoAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		dao = (MinicursoDAO) ctx.getBean("minicursoDAO");
		minicurso = minicurso == null ? new Minicurso() : minicurso;		
	}

	/**
	 *  Retorna a string "listMinicursos" que será lida pelo Struts. O Struts abre
	 * o arquivo struts.xml e vê qual é o result que está sendo enviado (no caso
	 * a listMinicursos) e redireciona para a página listMinicursos.jsp
	 * @return "listMinicursos" para o struts
	 */
	public String listMinicursos() {
		minicursos = dao.selectAll();
		return "listMinicursos";
	}

	/**
	 *  Insere o minicurso no banco de dados. Esse método é chamado pelo form
	 *  da página insertMinicurso.jsp
	 * @return "listMinicursos" para o struts
	 */
	public String insertMinicurso() {		
		boolean error = false;
		
		// Testa se o ID nao foi digitado
		if (minicurso.getId() != null && minicurso.getId() > 0) {
			addActionError("Id deve ficar em branco");
			error = true;
		}
		
		// Verifica se foi digitado o nome
		if (minicurso.getNome() == null || minicurso.getNome().length()==0) {
			addActionError("Nome do minicurso não informado");
			error = true;
		}

		if (error)
			return "insertError";
		
		dao.save(minicurso);
		minicursos = dao.selectAll();
		return "listMinicursos";
	}

	/**
	 *  Recebe minicurso do banco
	 *  Redireciona para a página de edição do minicurso
	 * @return "editMinicurso" para o struts
	 */
	public String editMinicurso() {
		minicurso = dao.selectById(minicurso.getId());
		return "editMinicurso";
	}
	
	/**
	 * Altera os dados do minicurso
	 * @return "listMinicursos" para o struts
	 */
	public String updateMinicurso() {
		boolean error = false;
		
		// Verifica se foi digitado o nome
		if (minicurso.getNome() == null || minicurso.getNome().length()==0) {
			addActionError("Nome do minicurso não informado");
			error = true;
		}

		if (error)
			return "updateError";
		
		dao.save(minicurso);
		minicursos = dao.selectAll();

		return "listMinicursos";
	}
	
	/**
	 * Insere o usuário logado na secao na lista de usuários inscritos no minicurso
	 * @return "enterMinicurso" para o struts
	 */
	public String enterMinicurso(){
		
		//Certifica-se que todos os dados do minicurso foram carregados do banco
		minicurso = dao.selectById(minicurso.getId());
		
		//Usuario - Sera preenchido com os dados do usuario logado
		Usuario u = new Usuario();
		
		//Recebe o usuario que está logado (na sessao)
		HttpServletRequest request;
		HttpSession session;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		String tmpLogin = (String) session.getAttribute("login");
		
		//Le os dados do usuario logado no banco
		ApplicationContext ctxUsuario;
		UsuarioDAO daoUsuario;
		ctxUsuario = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		daoUsuario = (UsuarioDAO) ctxUsuario.getBean("usuarioDAO");
		u=daoUsuario.selectByLogin(tmpLogin);
		
		//Adiciona o usuario na lista de usuarios inscritos
		if (u!=null){
			if (minicurso.getUsuarios()==null)
				minicurso.setUsuario(new HashSet<Usuario>());
			minicurso.getUsuarios().add(u);
		}
		
		//Incrementa o numero de inscritos neste minicurso
		if (minicurso.getQtInscritos()==null) minicurso.setQtInscritos(new Integer(0));
		if (minicurso.getQtVagas()==null) minicurso.setQtVagas(new Integer(0));
		if (minicurso.getQtInscritos()<minicurso.getQtVagas())
			minicurso.addQtInscrito();
		else {
			addActionError("Minicurso lotado!");
			listMinicursos();
			return "listError";
		}
		
		//Salva a transacao
		dao.save(minicurso);
		
		//Atualiza lista de minicursos, para que a pagina de retorno mostre todos os minicursos
		listMinicursos();
		
		return "enterMinicurso";
	}
	
	/**
	 * Remove o usuário logado na secao na lista de usuários inscritos no minicurso
	 * @return "enterMinicurso" para o struts
	 */
	public String exitMinicurso(){
		
		//Certifica-se que os dados do minicurso estao carregados do banco
		minicurso = dao.selectById(minicurso.getId());
		
		//Usuario
		Usuario u = new Usuario();
		
		//Pega o usuario logado (na sessao)
		HttpServletRequest request;
		HttpSession session;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		String tmpLogin = (String) session.getAttribute("login");
		
		//Recebe os dados do usuario logado do banco
		ApplicationContext ctxUsuario;
		UsuarioDAO daoUsuario;
		ctxUsuario = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		daoUsuario = (UsuarioDAO) ctxUsuario.getBean("usuarioDAO");
		u=daoUsuario.selectByLogin(tmpLogin);
		
		//Remove usuario logado da lista de usuarios inscritos no minicurso
		if (u!=null){
			if (minicurso.getUsuarios()!=null){
				minicurso.getUsuarios().remove(u);
			}
		}
		
		//Diminui contador de usuarios inscritos no minicurso
		minicurso.delQtInscrito();
		
		//Salva a transacao
		dao.save(minicurso);
		
		//Atualiza lista de minicursos para que sejam mostrados todos na pagina de retorno
		listMinicursos();
		
		return "enterMinicurso";
	}
	
	/**
	 * Exclui o usuário do banco
	 */
	public String deleteMinicurso() {
		dao.remove(minicurso);
		minicursos = dao.selectAll();

		return "listMinicursos";
	}

	/**
	 * Mostra a página de detalhes do minicurso 
	 */
	public String details() {
		minicurso = dao.selectById(minicurso.getId());
		inscritos = new ArrayList<Usuario>();
		
		for (Usuario u : minicurso.getUsuarios())
			inscritos.add(u);
		
		return "detailsMinicurso";
	}
	
	/*
	 * =================================
	 * Setters e Getters
	 * =================================
	 */
	public Minicurso getMinicurso() {
		return minicurso;
	}

	public void setMinicurso(Minicurso minicurso) {
		this.minicurso = minicurso;
	}

	public List<Minicurso> getMinicursos() {
		return minicursos;
	}

	public void setMinicursos(List<Minicurso> minicursos) {
		this.minicursos = minicursos;
	}

	public String getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(String confirmacao) {
		this.confirmacao = confirmacao;
	}
	
	public String getStrData(){
		return new SimpleDateFormat("MM/dd/yyyy HH:mm").format(minicurso.getData());
	}
	
	public void setStrData(String strData){
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		try {
			minicurso.setData((Date) formatter.parse(strData));
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	public List<Usuario> getInscritos() {
		return inscritos;
	}

	public void setInscritos(List<Usuario> inscritos) {
		this.inscritos = inscritos;
	}

}
