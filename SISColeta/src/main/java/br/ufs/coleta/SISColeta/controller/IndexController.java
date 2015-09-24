package br.ufs.coleta.SISColeta.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.ufs.coleta.SISColeta.model.ColecaoDAO;
import br.ufs.coleta.SISColeta.model.ColetaDAO;
import br.ufs.coleta.SISColeta.model.EspecieDAO;
import br.ufs.coleta.SISColeta.model.RetiradaDAO;
import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.MapaColeta;
import br.ufs.coleta.SISColeta.entities.Usuario;

@WebServlet("/IndexJsonDataServlet")
public class IndexController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private ColetaDAO coletaDAO;
	@EJB
    private EspecieDAO especieDAO;
	@EJB
    private RetiradaDAO retiradaDAO;
	@EJB
    private ColecaoDAO colecaoDAO;
	private Usuario usuario;
	

    public IndexController() {
    	super();
    }
    
    protected void doGet(HttpServletRequest request,
    		   HttpServletResponse response) throws ServletException, IOException {
         
    		if(request.getParameter("mapa") != null){
	    		List<MapaColeta> mapa = this.pontos();
	    		Gson gson = new Gson();
	    		String jsonString = gson.toJson(mapa);
	    		response.setContentType("application/json");
	    		response.getWriter().write(jsonString);
    		}
    		if(request.getParameter("chartproducao") != null){
				Integer[] producao = this.chartData();
				Gson gson = new Gson();
				String jsonString = gson.toJson(producao);
				response.setContentType("application/json");
				response.getWriter().write(jsonString);
    		}
    		if(request.getParameter("maiscoletados") != null){
    			List<Object[]> mc = especieDAO.getMaisColetadas();
    			Gson gson = new Gson();
				String jsonString = gson.toJson(mc);
				response.setContentType("application/json");
				response.getWriter().write(jsonString);
    		}
    		
    		if(request.getParameter("emprestimos") != null){
    			Object emp = retiradaDAO.emprestimosCount();
    			Gson gson = new Gson();
				String jsonString = gson.toJson(emp);
				response.setContentType("application/json");
				response.getWriter().write(jsonString);
    		}
    		
    		if(request.getParameter("producao") != null){
    			Object pc = coletaDAO.producaoColecao();
    			Gson gson = new Gson();
				String jsonString = gson.toJson(pc);
				response.setContentType("application/json");
				response.getWriter().write(jsonString);
    		}
    		
    		if(request.getParameter("pontos") != null){
    			int ponto = coletaDAO.pontosCount();
    			Gson gson = new Gson();
				String jsonString = gson.toJson(ponto);
				response.setContentType("application/json");
				response.getWriter().write(jsonString);
    		}
    		
    		if(request.getParameter("especie") != null){
    			Object esp = colecaoDAO.especieCount();
    			Gson gson = new Gson();
				String jsonString = gson.toJson(esp);
				response.setContentType("application/json");
				response.getWriter().write(jsonString);
    		}
    		
    		if(request.getParameter("determinador") != null){
    			Object esp = colecaoDAO.determinadorColetaCount(usuario.getId());
    			Gson gson = new Gson();
				String jsonString = gson.toJson(esp);
				response.setContentType("application/json");
				response.getWriter().write(jsonString);
    		}
    		
    		if(request.getParameter("participacao") != null){
    			Object esp = colecaoDAO.participacaoColetaCount(usuario.getId());
    			Gson gson = new Gson();
				String jsonString = gson.toJson(esp);
				response.setContentType("application/json");
				response.getWriter().write(jsonString);
    		}
    }
    
    public Integer[] chartData(){
    	Integer[] data = new Integer[7];
    	
    	int mes = Calendar.getInstance().get(Calendar.MONTH); 
    	int ano = 0; 	
    	for(int i=0; i<7; i++){
    		int mesIndex = (mes+2 -7+i)% 12;
    		if(mes+1<mesIndex){
    			ano = Calendar.getInstance().get(Calendar.YEAR) - 1;
    		}
    		else{
    			ano = Calendar.getInstance().get(Calendar.YEAR);
    		}
    		data[i] = coletaDAO.chartProducaoMes(mesIndex, ano);
    	}
    	return data;
    }
    
    public List<MapaColeta> pontos(){
    	List<Coleta> coletas = coletaDAO.getLimited(100);
    	List<MapaColeta> pontos = new ArrayList<MapaColeta>();
    	for(Coleta coleta: coletas){
    		MapaColeta mc = new MapaColeta();
    		mc.setLatitude(coleta.getLatitudeGrau(), coleta.getLatitudeMinuto(), coleta.getLatitudeSegundo(), coleta.getDirecaoLatitude());
    		mc.setLongitude(coleta.getLongitudeGrau(), coleta.getLongitudeMinuto(), coleta.getLongitudeSegundo(), coleta.getDirecaoLongitude());
    		mc.setLocal(coleta.getCodColeta());
    		mc.setData(coleta.getDataInicio());
    		pontos.add(mc);
    	}
    	return pontos;
    }
    
	public void insertUsuario(Usuario usuario){
		this.usuario = usuario;
	}

}
