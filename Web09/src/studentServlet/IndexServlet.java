package studentServlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Pagination;
import dao.StudentDao;
import entity.Student;

public class IndexServlet extends HttpServlet {

	StudentDao stuDao = new StudentDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String type = request.getParameter("type");
			if (type == null) {
				request.getRequestDispatcher("WEB-INF/index/index.jsp")

				.forward(request, response);

			} else if (type.equals("left")) {

				request.getRequestDispatcher("WEB-INF/index/left.jsp").forward(
						request, response);
			} else if (type.equals("stus")) {

				request.getRequestDispatcher("WEB-INF/index/stus.jsp").forward(
						request, response);

			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
