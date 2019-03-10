package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
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
 * Servlet implementation class IsValidPromoCode
 */
@WebServlet("/IsValidPromoCode")
public class IsValidPromoCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PromoCodeService pcService;
	//
	private Gson gson = new GsonBuilder().create();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IsValidPromoCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String originLat = request.getParameter("originLat");
		String originLong = request.getParameter("originLong");
		String destinationLat = request.getParameter("destLat");
		String destinationLong = request.getParameter("destLong");
		String id = request.getParameter("id");
		double originLatD = Double.parseDouble(originLat);
		double originLongD = Double.parseDouble(originLong);
		double destLatD = Double.parseDouble(destinationLat);
		double destLongD = Double.parseDouble(destinationLong);
		PromoCode promoCode = pcService.getPromoCodeById(Integer.parseInt(id));
		PrintWriter out = response.getWriter();
		if (pcService.isPromoCodeValid(originLatD, originLongD, destLatD, destLongD, promoCode) ) {
			out.print(gson.toJson(promoCode, PromoCode.class));
		}
		else {
			out.print("The pickup or the destination location is not within the radius of the location of the event that the Promo Code is intented for");
		}
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
