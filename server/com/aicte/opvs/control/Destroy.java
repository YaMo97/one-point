package com.aicte.opvs.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aicte.opvs.helpers.HttpGet;
import com.aicte.opvs.helpers.JSONReply;

/**
 * Servlet implementation class Destroy
 */
@WebServlet("/Destroy")
public class Destroy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Destroy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
HttpSession session = request.getSession(false);
		
		JSONObject replyJSON = new JSONObject();
		
		if (session == null) {
			replyJSON = JSONReply.generateReplyJSON(0, "ERROR", new JSONArray(), "Session Already Destroyed");
		}
		else {
			session.invalidate();
			replyJSON = JSONReply.generateReplyJSON(1, "SUCCESS", new JSONArray(), "Session Destroyed");
		}
		
		HttpGet.replyClient(response, replyJSON.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
