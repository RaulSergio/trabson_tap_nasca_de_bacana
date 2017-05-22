package repository;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JOptionPane;

import helper.JsonHelper;
import model.Produto;

public class TestProdutoRepository {
	
	public static JsonHelper jsonHelper = new JsonHelper();
	
	static ProdutoRepository pr = new ProdutoRepositoryBanco();
	
	public static void main(String[] args){
		//deveCadastrar();
		//deveBuscarTodosPorNome();
		//deveBuscarTodosPorId();
		//deveAlterar();
		//deveBuscarPorId();
		//deveExcluir();
		//mostraMaisCaro();
		//mostraMaisBarato();
		//mostraMedia();
		//mostraSoma();
	}
	
	public static void deveCadastrar(){
		Produto p=new Produto();
		p.setNome("Pi");
		p.setValor(3.14);
		
		pr.cadastrar(p);		
	}
	
	public static void deveBuscarTodosPorNome(){
		List<Produto> todos= pr.buscarTodosPorNome();
		
		try {
			JOptionPane.showMessageDialog(null,jsonHelper.gerarJsonLista(todos));
		} catch (HeadlessException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deveBuscarTodosPorId(){
		List<Produto> todos= pr.buscarTodosPorId();
		
		try {
			JOptionPane.showMessageDialog(null,jsonHelper.gerarJsonLista(todos));
		} catch (HeadlessException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deveAlterar(){
		
		Produto p = new Produto();
		p.setNome("piao");
		p.setValor(3.455);
		p.setId(6);
		
		pr.alterar(p);
	}
	
public static void deveBuscarPorId(){
		
		Produto  p = pr.buscarPorId(6);
		try {
			JOptionPane.showMessageDialog(null, jsonHelper.gerarJson(p));
		} catch (HeadlessException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deveExcluir(){
	
		pr.excluir(1);
	}
	
	public static void mostraMaisCaro(){
		Produto p =pr.maisCaro();
		try {
			JOptionPane.showMessageDialog(null, jsonHelper.gerarJson(p));
		} catch (HeadlessException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void mostraMaisBarato(){
		Produto p =pr.maisBarato();
		try {
			JOptionPane.showMessageDialog(null, jsonHelper.gerarJson(p));
		} catch (HeadlessException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void mostraMedia(){
		try {
			JOptionPane.showMessageDialog(null, jsonHelper.gerarJson(pr.media()));
		} catch (HeadlessException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void mostraSoma(){
		try {
			JOptionPane.showMessageDialog(null, jsonHelper.gerarJson(pr.soma()));
		} catch (HeadlessException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
