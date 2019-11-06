package control;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.PessoaDAO;
import model.Pessoa;
import util.FabricaConexao;

@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private Boolean editando;
	private Pessoa pessoaAntesDaEdicao;
	
	public PessoaBean() {
		this.pessoa = new Pessoa();
		this.pessoas = new ArrayList<Pessoa>();
		this.editando = false;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.pessoa = new Pessoa();
	}
	
	public void Editar(Pessoa _pessoa) {
		this.pessoaAntesDaEdicao = _pessoa.clone();
		this.pessoa = _pessoa;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.pessoa.restaurarPessoa(this.pessoaAntesDaEdicao);
		this.pessoa = new Pessoa();
		this.editando = false;
	}
	
	

	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		this.pessoa = new Pessoa();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			PessoaDAO dao = new PessoaDAO(conexao);
			
			dao.Inserir(this.pessoa);
			
			List<Pessoa> listaPessoas = dao.listarTodos();
			this.pessoas = listaPessoas;
			
			this.pessoa = new Pessoa();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void init() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			PessoaDAO dao = new PessoaDAO(conexao);
			
			this.pessoas = dao.listarTodos();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
