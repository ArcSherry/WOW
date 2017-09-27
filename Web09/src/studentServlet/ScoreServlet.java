package studentServlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BanJiDao;
import dao.ScoreDao;
import dao.SubjectDao;
import util.Pagination;
import entity.BanJi;
import entity.Score;
import entity.Student;
import entity.Subject;

public class ScoreServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null || type.equals("search")) {
				search(request, response);
			}else if (type.equals("manage")) {
				manage(request, response);
			}
			// } else if (type.equals("delete")) {
			// delete(request, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void manage(HttpServletRequest request, HttpServletResponse response) {
		String stuName = request.getParameter("name");
		ScoreDao scDao = new ScoreDao();
		BanJiDao bjDao = new BanJiDao();
		SubjectDao subDao = new SubjectDao();
		// int banji_id = 0;
		// if (request.getParameter("banji_id") != null) {
		// banji_id = Integer.parseInt(request.getParameter("banji_id"));
		// }
		// int sub_id = 0;
		// if (request.getParameter("sub_id") != null) {
		// sub_id = Integer.parseInt(request.getParameter("sub_id"));
		// }
		// bj,sub,why判断是否为空
		int banji_id = 0;
		if (request.getParameter("bj") != null) {
			banji_id = Integer.parseInt(request.getParameter("bj"));
		}
		int sub_id = 0;
		if (request.getParameter("sub") != null) {
			sub_id = Integer.parseInt(request.getParameter("sub"));
		}
		// new一个对象，把满足条件的放进去
		Score condition = new Score();
		Student stu = new Student();
		//学生的姓名
		stu.setName(stuName);
		BanJi bj = new BanJi();
		bj.setBanji_id(banji_id);
		stu.setBj(bj);
		condition.setStu(stu);
		Subject sub = new Subject();
		sub.setId(sub_id);
		condition.setSub(sub);
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		// condition从servlet传过来的
		int total = scDao.SearchCount(condition);
		int pageNum = 2;
		// private int yeMa; 显示几个页码，如1——5,1——10
		int yeMa = 5;
		int begin = pageNum * (ye - 1);
		// 这里能拿到满足condition的对象，begin，pageNum;
		Pagination p = new Pagination(ye, total, pageNum, yeMa);
		// 什么意思？
		ye = p.getYe();
		List<Score> list = scDao.SearchByCondition(condition, begin, pageNum);
		List<BanJi> bjlist =bjDao.SearchAll();
		List<Subject> sublist =subDao.SearchAll();
		request.setAttribute("scs", list);
		request.setAttribute("p", p);
		request.setAttribute("condition", condition);
		request.setAttribute("subs", sublist);
		request.setAttribute("bjs", bjlist);
		// 之后要进行转发
		try {
			request.getRequestDispatcher("WEB-INF/score/manage.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		String stuName = request.getParameter("name");
		ScoreDao scDao = new ScoreDao();
		BanJiDao bjDao = new BanJiDao();
		SubjectDao subDao = new SubjectDao();
		// int banji_id = 0;
		// if (request.getParameter("banji_id") != null) {
		// banji_id = Integer.parseInt(request.getParameter("banji_id"));
		// }
		// int sub_id = 0;
		// if (request.getParameter("sub_id") != null) {
		// sub_id = Integer.parseInt(request.getParameter("sub_id"));
		// }
		// bj,sub,why判断是否为空
		int banji_id = 0;
		if (request.getParameter("bj") != null) {
			banji_id = Integer.parseInt(request.getParameter("bj"));
		}
		int sub_id = 0;
		if (request.getParameter("sub") != null) {
			sub_id = Integer.parseInt(request.getParameter("sub"));
		}
		// new一个对象，把满足条件的放进去
		Score condition = new Score();
		Student stu = new Student();
		stu.setName(stuName);
		BanJi bj = new BanJi();
		bj.setBanji_id(banji_id);
		stu.setBj(bj);
		condition.setStu(stu);
		Subject sub = new Subject();
		sub.setId(sub_id);
		condition.setSub(sub);
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		// condition从servlet传过来的
		int total = scDao.SearchCount(condition);
		int pageNum = 2;
		// private int yeMa; 显示几个页码，如1——5,1——10
		int yeMa = 5;
		int begin = pageNum * (ye - 1);
		// 这里能拿到满足condition的对象，begin，pageNum;
		Pagination p = new Pagination(ye, total, pageNum, yeMa);
		// 什么意思？
		ye = p.getYe();
		List<Score> list = scDao.SearchByCondition(condition, begin, pageNum);
		List<BanJi> bjlist =bjDao.SearchAll();
		List<Subject> sublist =subDao.SearchAll();
		request.setAttribute("scs", list);
		request.setAttribute("p", p);
		request.setAttribute("condition", condition);
		request.setAttribute("subs", sublist);
		request.setAttribute("bjs", bjlist);
		// 之后要进行转发
		try {
			request.getRequestDispatcher("WEB-INF/score/list.jsp").forward(
					request, response);
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
