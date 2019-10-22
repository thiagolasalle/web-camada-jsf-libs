package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Endereco;
import model.Pessoa;

public class PessoaDAO implements PessoaInDAO {

	private Connection conexao = null;
	
	public PessoaDAO(Connection _conn) {
		this.conexao = _conn;
	}
	
	@Override
	public void Inserir(Pessoa _objeto) throws SQLException {
		
		String SQL = "INSERT INTO pessoa (nome, email, tel) VALUES (?, ?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setString(1, _objeto.getNome());
		ps.setString(2, _objeto.getEmail());
		ps.setString(3, _objeto.getTelefone());
		
		ps.execute();

	}

	@Override
	public List<Pessoa> listarTodos() throws SQLException {
		
		//System.out.println();
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		ResultSet rs = null;
		
		String SQL = "SELECT id, nome, email, tel FROM pessoa";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			
			int id = rs.getInt(1);
			String nome = rs.getString(2);
			String email = rs.getString(3);
			String telefone = rs.getString(4);
			
			EnderecoDAO daoEnd = new EnderecoDAO(this.conexao);
			List<Endereco> enderecos = daoEnd.listarEnderecosPorPessoa(id);
			
			Pessoa p = new Pessoa(id, nome, email, telefone, enderecos);
			
			pessoas.add(p);
		}
		
		return pessoas;
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
	
		String SQL = "DELETE FROM pessoa WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		return ps.execute();
	}

	@Override
	public Boolean Atualizar(Pessoa _objeto) throws SQLException {

		String SQL = "UPDATE pessoa SET nome = ?, email = ?, tel = ? WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setString(1, _objeto.getNome());
		ps.setString(2, _objeto.getEmail());
		ps.setString(3, _objeto.getTelefone());
		
		ps.setInt(4, _objeto.getId());
		
		return ps.execute();
	}

	@Override
	public Pessoa buscarPorId(int _id) throws SQLException {
		
		ResultSet rs = null;
		Pessoa p = null;
		
		String SQL = "SELECT id, nome, email, tel FROM pessoa WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.executeQuery();
		
		if (rs.next()) {
			
			int id = rs.getInt(1);
			String nome = rs.getString(2);
			String email = rs.getString(3);
			String telefone = rs.getString(4);
			
			EnderecoDAO daoEnd = new EnderecoDAO(this.conexao);
			List<Endereco> enderecos = daoEnd.listarEnderecosPorPessoa(id);
			
			p = new Pessoa(id, nome, email, telefone, enderecos);
		}
		
		return p;
	}

}
