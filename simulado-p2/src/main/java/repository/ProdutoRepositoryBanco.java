package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Valor;
import model.Produto;


public class ProdutoRepositoryBanco implements ProdutoRepository {

	private Connection conexao = ConexaoFactory.criarConexao();

	@Override
	public void cadastrar(Produto usuario) {
		String sql = "insert into produto (nome,valor) values (?,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setDouble(2, usuario.getValor());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Produto> buscarTodosPorNome() {

		List<Produto> lista = new ArrayList<>();

		try {
			
			String sql = "select * from produto order by nome";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				int id = result.getInt("id");
				String nome = result.getString("nome");
				Double valor = Double.parseDouble(result.getString("valor"));

				Produto p = new Produto();
				p.setId(id);
				p.setNome(nome);
				p.setValor(valor);

				lista.add(p);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Produto> buscarTodosPorValor() {

		List<Produto> lista = new ArrayList<>();

		try {
			
			String sql = "select * from produto order by valor";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				int id = result.getInt("id");
				String nome = result.getString("nome");
				Double valor = Double.parseDouble(result.getString("valor"));

				Produto p = new Produto();
				p.setId(id);
				p.setNome(nome);
				p.setValor(valor);

				lista.add(p);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
	@Override
	public List<Produto> buscarTodosPorId() {

		List<Produto> lista = new ArrayList<>();

		try {
			
			String sql = "select * from produto order by id";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				int id = result.getInt("id");
				String nome = result.getString("nome");
				Double valor = Double.parseDouble(result.getString("valor"));

				Produto p = new Produto();
				p.setId(id);
				p.setNome(nome);
				p.setValor(valor);

				lista.add(p);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
	@Override
	public void alterar(Produto usuario) {
		String sql = "update produto set nome=?,valor=? where id=?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setDouble(2, usuario.getValor());
			ps.setInt(3, usuario.getId());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Produto buscarPorId(Integer id) {	

		try {

			String sql = ("select * from produto where id = ?");
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			ResultSet result = prepareStatement.executeQuery();
			JOptionPane.showMessageDialog(null, id);

			if (result.next()) {
				int idprod = result.getInt("id");
				String nome = result.getString("nome");
				Double valor = Double.parseDouble(result.getString("valor"));

				Produto p = new Produto();
				p.setId(idprod);
				p.setNome(nome);
				p.setValor(valor);
				
				result.close();
				prepareStatement.close();
				
				return p;
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public void excluir(int id) {
		try {
			String sql = "delete from produto where id=?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			prepareStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(Produto produto) {
		if (produto.getId() == null) {
			cadastrar(produto);
		} else {
			alterar(produto);
		}

	}
	@Override
	public Produto maisCaro() {
		try {

			String sql = "select * from produto where valor=(select max(valor) from produto)";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();		

			if (result.next()) {
				int idprod = result.getInt("id");
				String nome = result.getString("nome");
				Double valor = result.getDouble("valor");
				
				Produto p = new Produto();
				p.setId(idprod);
				p.setNome(nome);
				p.setValor(valor);
				
				JOptionPane.showMessageDialog(null, "id: "+p.getId()+"\nnome: "+p.getNome()+"\nvalor: "+p.getValor());
				return p;
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Produto maisBarato() {
		try {

			String sql = "select * from produto where valor=(select min(valor) from produto)";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();		

			if (result.next()) {
				int idprod = result.getInt("id");
				String nome = result.getString("nome");
				Double valor = result.getDouble("valor");
				
				Produto p = new Produto();
				p.setId(idprod);
				p.setNome(nome);
				p.setValor(valor);
				
				JOptionPane.showMessageDialog(null, "id: "+p.getId()+"\nnome: "+p.getNome()+"\nvalor: "+p.getValor());
				return p;
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Valor media() {
		try {

			String sql = "select avg(valor) from produto";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			if (result.next()) {				
				double res = result.getDouble("avg");	
				Valor valor = new Valor();
				valor.setValor(res);
				return valor;
				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Valor soma() {
		try {

			String sql = "select sum(valor) from produto";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			if (result.next()) {				
				double res = result.getDouble("sum");	
				Valor valor = new Valor();
				valor.setValor(res);
				return valor;
				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
