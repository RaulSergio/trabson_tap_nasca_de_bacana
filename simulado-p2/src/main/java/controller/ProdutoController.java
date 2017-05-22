package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import helper.JsonHelper;
import model.Produto;
import repository.ProdutoRepository;
import repository.ProdutoRepositoryBanco;

@WebServlet(urlPatterns = "/prodcontroller")
public class ProdutoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProdutoRepository produtoRepository = new ProdutoRepositoryBanco();

	private JsonHelper jsonHelper = new JsonHelper();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Capturando o que vem do client
		String nome = req.getParameter("nome");
		Double valor = Double.parseDouble(req.getParameter("valor"));

		// Instanciando objeto
		Produto prod = new Produto(nome, valor);

		// Inserir na lista
		produtoRepository.cadastrar(prod);
		try {
			resp.getWriter().println(jsonHelper.gerarJson(prod));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json;
		String op = req.getParameter("op");
		//busca mais caro
		if (op.equals("caro")) {

			try {
				json = jsonHelper.gerarJson(produtoRepository.maisCaro());
				resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//busca mais barato
		} else if (op.equals("barato")) {

			try {
				json = jsonHelper.gerarJson(produtoRepository.maisBarato());
				resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (op.equals("media")) {

			try {
				json = jsonHelper.gerarJson(produtoRepository.media());
				resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (op.equals("soma")) {

			try {
				json = jsonHelper.gerarJson(produtoRepository.soma());
				resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (op.equals("id")) {
			int idbusca = Integer.parseInt(req.getParameter("id"));
			
			try {
				json = jsonHelper.gerarJson(produtoRepository.buscarPorId(idbusca));
				resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (op.equals("buscatodos")) {
			String ordem = req.getParameter("ordem");
			
			if(ordem.equals("nome")){
				try {
					json = jsonHelper.gerarJsonLista(produtoRepository.buscarTodosPorNome());
					JOptionPane.showMessageDialog(null, json);
					resp.getWriter().print(json);
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(ordem.equals("id")){
				try {
					json = jsonHelper.gerarJsonLista(produtoRepository.buscarTodosPorId());
					resp.getWriter().print(json);
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(ordem.equals("valor")){
				try {
					json = jsonHelper.gerarJsonLista(produtoRepository.buscarTodosPorValor());
					resp.getWriter().print(json);
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idUsu = req.getParameter("id");
		// capturando o indice do objeto a ser alterado
		Integer id = Integer.parseInt(idUsu);

		// Capturando dados a serem alterados
		String nome = req.getParameter("nome");
		Double valor = Double.parseDouble(req.getParameter("valor"));

		// Colocando dados da tela em objeto usuario
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome(nome);
		produto.setValor(valor);

		produtoRepository.alterar(produto);

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// capturando o indice do objeto a ser excluido
		int id = Integer.parseInt(req.getParameter("id"));
		// removendo objeto do array
		produtoRepository.excluir(id);

	}

}
