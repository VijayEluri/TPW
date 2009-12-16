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

import beans.Evento;
import beans.Usuario;

import com.opensymphony.xwork2.ActionSupport;

import daos.EventoDAO;
import daos.UsuarioDAO;

/**
 * Classe responsável por cuidar das acoes do Evento.
 * Geralmente chamadas pelo struts. 
 * @author vendra
 *
 */
public class EventoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * Evento relacionado a esta Action
	 */
	private Evento evento;
	
	/**
	 * Lista de eventos
	 */
	private List<Evento> eventos;
	
	/**
	 *  Lista de inscritos no evento
	 */
	private List<Usuario> inscritos;
	
	//Dao para conexao com o banco
	private EventoDAO dao;

	//Spring
	private ApplicationContext ctx;

	// Confirmação da password
	private String confirmacao;
	
	/**
	 * Construtor: Inicializa o evento, dao e ctx
	 */
	public EventoAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		dao = (EventoDAO) ctx.getBean("eventoDAO");
		evento = evento == null ? new Evento() : evento;		
	}

	/** 
	 * Retorna a string "listEventos" que será lida pelo Struts. O Struts abre
	 * o arquivo struts.xml e vê qual é o result que está sendo enviado (no caso
	 * a listEventos) e redireciona para a página listEventos.jsp
	 * @return "listEventos" para o struts
	*/
	public String listEventos() {
		eventos = dao.selectAll();
		return "listEventos";
	}

	/**
	 *  Insere o evento no banco de dados. Esse método é chamado pelo form
	 *  da página insertEvento.jsp
	 *  @return "listEventos" para o struts
	 */
	public String insertEvento() {		
		boolean error = false;
		
		// Testa se o ID nao foi digitado
		if (evento.getId() != null && evento.getId() > 0) {
			addActionError("Id deve ficar em branco");
			error = true;
		}
		
		// Verifica se foi digitado o nome
		if (evento.getNome() == null || evento.getNome().length()==0) {
			addActionError("Nome do evento não informado");
			error = true;
		}

		if (error)
			return "insertError";

		if (evento.getQtInscritos()==null)
			evento.setQtInscritos(new Integer(0));
		
		dao.save(evento);
		eventos = dao.selectAll();
		return "listEventos";
	}

	/**
	 * Recebe evento do banco
	 *  Redireciona para a página de edição do usuário
	 *  @return "editEvento" para o struts
	 */
	public String editEvento() {
		evento = dao.selectById(evento.getId());
		return "editEvento";
	}
	
	/**
	 * Insere o usuário logado na secao na lista de usuários inscritos no evento
	 * @return "enterEvento" para o struts
	 */
	public String enterEvento(){
		
		//Certifica-se que todos os dados do evento foram carregados do banco
		evento = dao.selectById(evento.getId());
		
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
			if (evento.getUsuarios()==null)
				evento.setUsuario(new HashSet<Usuario>());
			evento.getUsuarios().add(u);
		}
		
		//Incrementa o numero de inscritos neste evento
		if (evento.getQtInscritos()==null) evento.setQtInscritos(new Integer(0));
		if (evento.getQtVagas()==null) evento.setQtVagas(new Integer(0));
		if (evento.getQtInscritos()<evento.getQtVagas())
			evento.addQtInscrito();
		else {
			addActionError("Evento lotado!");
			listEventos();
			return "listError";
		}
		
		//Salva a transacao
		dao.save(evento);
		
		//Atualiza lista de eventos, para que a pagina de retorno mostre todos os eventos
		listEventos();
		
		return "enterEvento";
	}
	
	/**
	 * Remove o usuário logado na secao na lista de usuários inscritos no evento
	 * @return "enterEvento" para o struts
	 */
	public String exitEvento(){
		
		//Certifica-se que os dados do evento estao carregados do banco
		evento = dao.selectById(evento.getId());
		
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
		
		//Remove usuario logado da lista de usuarios inscritos no evento
		if (u!=null){
			if (evento.getUsuarios()!=null){
				evento.getUsuarios().remove(u);
			}
		}
		
		//Diminui contador de usuarios inscritos no evento
		evento.delQtInscrito();
		
		//Salva a transacao
		dao.save(evento);
		
		//Atualiza lista de eventos para que sejam mostrados todos na pagina de retorno
		listEventos();
		
		return "enterEvento";
	}
	
	/**
	 * Atualiza dados do Evento
	 * @return "listEventos" para o struts
	 */
	public String updateEvento() {
		boolean error = false;
		
		// Verifica se foi digitado o nome
		if (evento.getNome() == null || evento.getNome().length()==0) {
			addActionError("Nome do evento não informado");
			error = true;
		}

		if (error)
			return "updateError";
		
		dao.save(evento);
		eventos = dao.selectAll();

		return "listEventos";
	}
	
	/**
	 * Remove o evento do banco
	 * @return "listEventos" para o struts
	 */
	public String deleteEvento() {
		dao.remove(evento);
		eventos = dao.selectAll();

		return "listEventos";
	}

	/**
	 * Mostra a página de detalhes do evento 
	 */
	public String details() {
		evento = dao.selectById(evento.getId());
		inscritos = new ArrayList<Usuario>();
		
		for (Usuario u : evento.getUsuarios())
			inscritos.add(u);
		
		return "detailsEvento";
	}
	
	/*
	 * ===============================
	 * Setters e Getters
	 * ===============================
	 */
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public String getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(String confirmacao) {
		this.confirmacao = confirmacao;
	}
	
	public String getStrData(){
		return new SimpleDateFormat("MM/dd/yyyy HH:mm").format(evento.getData());
	}
	
	public void setStrData(String strData){
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		try {
			evento.setData((Date) formatter.parse(strData));
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