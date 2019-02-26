package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import models.PromoCode;
import services.PromoCodeService;

/**
 * Servlet implementation class PromoCodes
 */
@WebServlet("/PromoCodes")
public class PromoCodes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//
	@EJB
	PromoCodeService pcService;
	//
	private Gson gson = new GsonBuilder().create();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromoCodes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<PromoCode> codes = pcService.getPromoCodes();
		request.setAttribute("PROMO_CODES_LIST", codes);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(codes, new TypeToken<List<PromoCode>>() {}.getType()));
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
