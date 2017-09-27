package studentServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import util.Pagination;
import dao.BanJiDao;
import dao.SubjectDao;
import entity.BanJi;
import entity.Student;
import entity.Subject;

public class SubjectServlet extends HttpServlet {

	SubjectDao subDao;
	Subject sub = new Subject();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String type = request.getParameter("type");

		if (type == null || type.equals("search")) {
			search(request, response);
		} else if (type.equals("showAdd")) {
			showAdd(request, response);
		} else if (type.equals("add")) {
			add(request, response);
		} else if (type.equals("showModify")) {
			showModify(request, response);
		} else if (type.equals("modify")) {
			modify(request, response);
		} else if (type.equals("delete")) {
			delete(request, response);
		} else if (type.equals("searchSubByBj")) {
			searchSubByBj(request, response);
		}
	}

	private void searchSubByBj(HttpServletRequest request,
			HttpServletResponse response) {
		// 这个根据bj查科目的方法怎么写
		int bjId = Integer.parseInt(request.getParameter("bjId"));
		// first json的方法
		List<Subject> list = subDao.searchByBjId(bjId);
		JSONArray json = JSONArray.fromObject(list);
		try {
			PrintWriter out =response.getWriter();
			out.print(json.toString());
			//写不写都可以，默认的就是toString()     out.print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	

	private void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			Subject condition = new Subject();
			String name = request.getParameter("sub_name");
			if (!"".equals(name)) {
				condition.setSub_name(name);
			}
			int ye = 1;
			if (null != request.getParameter("ye")) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int pageNum = 2;
			int yeMa = 5;
			int total = subDao.SearchCount(condition);
			int maxYe = 0;
			maxYe = total % pageNum == 0 ? total / pageNum : total / pageNum
					+ 1;
			Pagination pagination = new Pagination(ye, total, pageNum, yeMa);
			ye = pagination.getYe();
			int begin = pageNum * (ye - 1);
			List<Subject> list = subDao.searchByCondition(condition, begin,
					pageNum);
			request.setAttribute("p", pagination);
			request.setAttribute("condition", condition);
			request.setAttribute("subs", list);
			request.getRequestDispatcher("WEB-INF/subject/list.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameter("selectId").split(",");
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			flag = subDao.delete(Integer.parseInt(ids[i]));
			if (flag == false) {
				break;
			}
		}
		if (flag) {
			try {
				response.sendRedirect("subs");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// 拿参数
		try {

			String name = request.getParameter("sub_name");
			sub = new Subject();
			sub.setSub_name(name);
			// 把拿到的数据通过Dao层传给数据库
			boolean flag = subDao.add(sub);
			// 这里保存成功或是失败要给个提示
			if (flag) {
				// request.getRequestDispatcher("subs").forward(request,response);
				response.sendRedirect("subs");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/subject/add.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("selectId"));
			String name = request.getParameter("sub_name");
			sub = new Subject();
			sub.setId(id);
			sub.setSub_name(name);

			boolean flag = subDao.modify(sub);
			if (flag) {
				response.sendRedirect("subs");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showModify(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 我们要拿到selectId
			int id = Integer.parseInt(request.getParameter("selectId"));
			// 从Dao层里得到sub
			sub = new Subject();
			sub = subDao.SearchById(id);
			// System.out.println(sub + "1sub.getName99");

			request.setAttribute("subs", sub);
			request.getRequestDispatcher("WEB-INF/subject/modify.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
