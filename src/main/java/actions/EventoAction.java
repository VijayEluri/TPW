package actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

public class EventoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Evento evento;
	private List<Evento> eventos;

	private String strData;
	
	private EventoDAO dao;

	private ApplicationContext ctx;

	// Confirmação da password
	private String confirmacao;
	
	public EventoAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		dao = (EventoDAO) ctx.getBean("eventoDAO");
		evento = evento == null ? new Evento() : evento;		
	}

	// Retorna a string "listEventos" que será lida pelo Struts. O Struts abre
	// o arquivo struts.xml e vê qual é o result que está sendo enviado (no caso
	// a listEventos) e redireciona para a página listEventos.jsp
	public String listEventos() {
		eventos = dao.selectAll();
		return "listEventos";
	}

	// Insere o evento no banco de dados. Esse método é chamado pelo form
	// da página insertEvento.jsp
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
		
		dao.save(evento);
		eventos = dao.selectAll();
		return "listEventos";
	}

	// Redireciona para a página de edição do usuário
	public String editEvento() {
		evento = dao.selectById(evento.getId());
		return "editEvento";
	}
	
	public String enterEvento(){
		evento = dao.selectById(evento.getId());
		Usuario u = new Usuario();
		HttpServletRequest request;
		HttpSession session;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		String tmpLogin = (String) session.getAttribute("login");
		ApplicationContext ctxUsuario;
		UsuarioDAO daoUsuario;
		ctxUsuario = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		daoUsuario = (UsuarioDAO) ctx.getBean("usuarioDAO");
		u=daoUsuario.selectByLogin(tmpLogin);
		if (u!=null){
			if (evento.getUsuarios()==null)
				evento.setUsuario(new HashSet<Usuario>());
			evento.getUsuarios().add(u);
		}
		dao.save(evento);
		return "enterEvento";
	}
	
	public String exitEvento(){
		evento = dao.selectById(evento.getId());
		Usuario u = new Usuario();
		HttpServletRequest request;
		HttpSession session;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		String tmpLogin = (String) session.getAttribute("login");
		ApplicationContext ctxUsuario;
		UsuarioDAO daoUsuario;
		ctxUsuario = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		daoUsuario = (UsuarioDAO) ctx.getBean("usuarioDAO");
		u=daoUsuario.selectByLogin(tmpLogin);
		if (u!=null){
			if (evento.getUsuarios()!=null){
				evento.getUsuarios().remove(u);
			}
		}
		dao.save(evento);
		return "enterEvento";
	}
	
	// Altera os dados do usuário
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
	
	// Exclui o usuário
	public String deleteEvento() {
		dao.remove(evento);
		eventos = dao.selectAll();

		return "listEventos";
	}
	
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
		return new SimpleDateFormat("dd/MM/yyyy").format(evento.getData());
	}
	
	public void setStrData(String strData){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			evento.setData((Date) formatter.parse(strData));
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
}
