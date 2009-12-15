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

import beans.Minicurso;
import beans.Usuario;

import com.opensymphony.xwork2.ActionSupport;

import daos.MinicursoDAO;
import daos.UsuarioDAO;

public class MinicursoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Minicurso minicurso;
	private List<Minicurso> minicursos;

	private String strData;
	
	private MinicursoDAO dao;

	private ApplicationContext ctx;

	// Confirmação da password
	private String confirmacao;
	
	public MinicursoAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		dao = (MinicursoDAO) ctx.getBean("minicursoDAO");
		minicurso = minicurso == null ? new Minicurso() : minicurso;		
	}

	// Retorna a string "listMinicursos" que será lida pelo Struts. O Struts abre
	// o arquivo struts.xml e vê qual é o result que está sendo enviado (no caso
	// a listMinicursos) e redireciona para a página listMinicursos.jsp
	public String listMinicursos() {
		minicursos = dao.selectAll();
		return "listMinicursos";
	}

	// Insere o minicurso no banco de dados. Esse método é chamado pelo form
	// da página insertMinicurso.jsp
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

	// Redireciona para a página de edição do usuário
	public String editMinicurso() {
		minicurso = dao.selectById(minicurso.getId());
		return "editMinicurso";
	}
	
	// Altera os dados do usuário
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
	
	public String enterMinicurso(){
		minicurso = dao.selectById(minicurso.getId());
		Usuario u = new Usuario();
		HttpServletRequest request;
		HttpSession session;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		String tmpLogin = (String) session.getAttribute("login");
		ApplicationContext ctxUsuario;
		UsuarioDAO daoUsuario;
		ctxUsuario = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		daoUsuario = (UsuarioDAO) ctxUsuario.getBean("usuarioDAO");
		u=daoUsuario.selectByLogin(tmpLogin);
		if (u!=null){
			if (minicurso.getUsuarios()==null)
				minicurso.setUsuario(new HashSet<Usuario>());
			minicurso.getUsuarios().add(u);
		}
		if (minicurso.getQtInscritos()==null) minicurso.setQtInscritos(new Integer(0));
		if (minicurso.getQtVagas()==null) minicurso.setQtVagas(new Integer(0));
		if (minicurso.getQtInscritos()<minicurso.getQtVagas())
			minicurso.addQtInscrito();
		else {
			addActionError("Minicurso lotado!");
			listMinicursos();
			return "listError";
		}
		dao.save(minicurso);
		
		listMinicursos();
		
		return "enterMinicurso";
	}
	
	public String exitMinicurso(){
		minicurso = dao.selectById(minicurso.getId());
		Usuario u = new Usuario();
		HttpServletRequest request;
		HttpSession session;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		String tmpLogin = (String) session.getAttribute("login");
		ApplicationContext ctxUsuario;
		UsuarioDAO daoUsuario;
		ctxUsuario = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		daoUsuario = (UsuarioDAO) ctxUsuario.getBean("usuarioDAO");
		u=daoUsuario.selectByLogin(tmpLogin);
		if (u!=null){
			if (minicurso.getUsuarios()!=null){
				minicurso.getUsuarios().remove(u);
			}
		}
		minicurso.delQtInscrito();
		dao.save(minicurso);
		
		listMinicursos();
		
		return "enterMinicurso";
	}
	
	// Exclui o usuário
	public String deleteMinicurso() {
		dao.remove(minicurso);
		minicursos = dao.selectAll();

		return "listMinicursos";
	}
	
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
		return new SimpleDateFormat("dd/MM/yyyy").format(minicurso.getData());
	}
	
	public void setStrData(String strData){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			minicurso.setData((Date) formatter.parse(strData));
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
}
