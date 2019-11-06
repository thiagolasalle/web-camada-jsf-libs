package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String email;
	private String telefone;
	
	private List<Endereco> enderecos;
	
	public Pessoa() {
		this.enderecos = new ArrayList<Endereco>();
	}
	
	public Pessoa(int id, String nome, String email, String telefone, List<Endereco> enderecos) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.enderecos = enderecos;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public void restaurarPessoa(Pessoa _pessoa) {
		this.id = _pessoa.id;
		this.nome = _pessoa.nome;
		this.email = _pessoa.email;
		this.telefone = _pessoa.telefone;
		this.enderecos = _pessoa.enderecos;
	}
	
	@Override
	public Pessoa clone() {
		return new Pessoa(this.id, this.nome, this.email, this.telefone, this.enderecos);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Pessoa other = (Pessoa) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + "]";
	}
}
