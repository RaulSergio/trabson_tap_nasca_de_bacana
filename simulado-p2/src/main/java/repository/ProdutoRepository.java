package repository;

import java.util.List;

import model.Valor;
import model.Produto;


public interface ProdutoRepository {

	public void cadastrar(Produto produto) ;

	public List<Produto> buscarTodosPorNome() ;

	public List <Produto> buscarTodosPorValor() ;
	
	public List <Produto> buscarTodosPorId() ;
	
	public void alterar(Produto produto) ;
	
	public Produto buscarPorId(Integer id) ;
		
	public void excluir(int id) ;	
	
	public void salvar(Produto produto) ;
	
	public Produto maisCaro();
	
	public Produto maisBarato();
	
	public Valor media();
	
	public Valor soma();
}
