package actions;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Minicurso;

import com.opensymphony.xwork2.ActionSupport;

import daos.MinicursoDAO;

public class MinicursoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Minicurso minicurso;
	private List<Minicurso> minicursos;

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
	
}
