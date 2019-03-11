package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.PromoCode;
import services.PromoCodeService;

/**
 * Servlet implementation class GeneratePromoCode
 */
@WebServlet("/GeneratePromoCode")
public class GeneratePromoCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//
	@EJB
	PromoCodeService pcService;
	//
	Gson gson = new GsonBuilder().create();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneratePromoCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * RequestDispatcher view =
		 * request.getRequestDispatcher("WEB-INF/view/generate_promo_code.jsp");
		 * view.forward(request, response);
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		PromoCode body = gson.fromJson(br, PromoCode.class);
		pcService.addPromoCode(body);
	}

}
