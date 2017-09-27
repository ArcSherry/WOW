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
import dao.BjAndSubDao;
import dao.SubjectDao;
import entity.BanJi;
import entity.Student;
import entity.Subject;

public class BanJiServlet extends HttpServlet {

	SubjectDao subDao = new SubjectDao();
	Subject sub = new Subject();
	BjAndSubDao bsDao = new BjAndSubDao();
	BanJiDao bjDao = new BanJiDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
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
		} else if (type.equals("searchSub")) {
			searchSub(request, response);
		} else if (type.equals("deleteSub")) {
			deleteSub(request, response);
		} else if (type.equals("manageSub")) {
			manageSub(request, response);
		} else if (type.equals("addSub")) {
			addSub(request, response);
		}
	}
		private void deleteSub(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int bjId=0;
		if(!"".equals(request.getParameter("bjId"))&&null!=request.getParameter("bjId")){
		bjId = Integer.parseInt(request.getParameter("bjId"));
		}
		String[] subIds = request.getParameter("subIds").split(",");
		boolean flag = bsDao.deleteAll(bjId, subIds);
			try {
			PrintWriter out = response.getWriter();
			BanJi bj = bjDao.SearchByBjAndSubById(bjId);
		
			//查询出此班级已有的课程
			List<Subject> haveSubs = subDao.searchByCondition(bjId);
			String subStrHave="";
			for(int i=0;i<haveSubs.size();i++){
				subStrHave+=haveSubs.get(i).getId()+","+haveSubs.get(i).getSub_name()+";";
			}
			out.print(subStrHave.substring(0,subStrHave.length()-1)+"|");
			List<Subject> noSubs = subDao.SearchNoSubByBjId(bjId);
			String subStrNo="";
			for(int j=0;j<noSubs.size();j++){
				subStrNo+=noSubs.get(j).getId()+","+noSubs.get(j).getSub_name()+";";
			}
			out.print(subStrNo.substring(0,subStrNo.length()-1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void searchSub(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int bjId = Integer.parseInt(request.getParameter("banjiID"));
		try {
			PrintWriter out = response.getWriter();

			BanJi bj = bjDao.SearchByBjAndSubById(bjId);
			List<Subject> noSubs = subDao.SearchNoSubByBjId(bjId);

			String str = JSONArray.fromObject(bj.getSubs()).toString();
			str += "-|-" + JSONArray.fromObject(noSubs).toString();
			out.print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addSub(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int bjId=0;
		if(!"".equals(request.getParameter("bjId"))&&null!=request.getParameter("bjId")){
		bjId = Integer.parseInt(request.getParameter("bjId"));
		}
		String[] subIds = request.getParameter("subIds").split(",");
		boolean flag = bsDao.addAll(bjId, subIds);
		try {
			PrintWriter out = response.getWriter();

			BanJi bj = bjDao.SearchByBjAndSubById(bjId);
			
			//查询出此班级已有的课程
			List<Subject> haveSubs = subDao.searchByCondition(bjId);
			String subStrHave="";
			for(int i=0;i<haveSubs.size();i++){
				subStrHave+=haveSubs.get(i).getId()+","+haveSubs.get(i).getSub_name()+";";
			}
			out.print(subStrHave.substring(0,subStrHave.length()-1)+"|");
			List<Subject> noSubs = subDao.SearchNoSubByBjId(bjId);
			String subStrNo="";
			for(int j=0;j<noSubs.size();j++){
				subStrNo+=noSubs.get(j).getId()+","+noSubs.get(j).getSub_name()+";";
			}
			out.print(subStrNo.substring(0,subStrNo.length()-1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void manageSub(HttpServletRequest request,
			HttpServletResponse response) {
		List<BanJi> bjs = bjDao.SearchAll();
		int bjId = 0;
		if (request.getParameter("selectId") == null) {
			bjId = bjs.get(0).getBanji_id();
		} else {
			bjId = Integer.parseInt(request.getParameter("selectId"));
		}

		BanJi bj = bjDao.SearchByBjAndSubById(bjId);
		List<Subject> noSubs = subDao.SearchNoSubByBjId(bjId);//wrong
		
			request.setAttribute("bjId", bjId);
			request.setAttribute("bj", bj);
			request.setAttribute("noSubs", noSubs);
			request.setAttribute("bjs", bjs);
			try {
				request.getRequestDispatcher("WEB-INF/banji/manageSub.jsp")
						.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	

	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			BanJi condition = new BanJi();
			int id=0;
			String name = request.getParameter("banji_name");
			
			if(null!=request.getParameter("banji_id")&&!"".equals(request.getParameter("banji_id"))){
				id=Integer.parseInt(request.getParameter("banji_id"));
			}
			
			if (!"".equals(name)) {
				condition.setBanji_name(name);
			}
			condition.setBanji_id(id);
			int ye = 1;
			if (null != request.getParameter("ye")) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int pageNum = 2;
			int yeMa = 5;
			int total = bjDao.SearchCount(condition);
			int maxYe = 0;
			maxYe = total % pageNum == 0 ? total / pageNum : total / pageNum
					+ 1;
			Pagination pagination = new Pagination(ye, total, pageNum, yeMa);
			ye = pagination.getYe();
			int begin = pageNum * (ye - 1);
			//一个searchAll是查的所有，在下拉框里显示，而searchByCondition是按条件查的
			List<BanJi> list = bjDao.searchByCondition(condition, begin,
					pageNum);
			List<BanJi> list2 = bjDao.SearchAll();
			request.setAttribute("p", pagination);
			request.setAttribute("condition", condition);
			request.setAttribute("bjs", list);
			request.setAttribute("bjAll", list2);
			request.getRequestDispatcher("WEB-INF/banji/list.jsp").forward(
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
