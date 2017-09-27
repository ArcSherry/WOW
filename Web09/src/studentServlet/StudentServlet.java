package studentServlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.Pagination;
import dao.BanJiDao;
import dao.StudentDao;
import entity.BanJi;
import entity.Student;

public class StudentServlet extends HttpServlet {
	StudentDao stuDao = new StudentDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String type = request.getParameter("type");
		System.out.println(type);
		// Method[] methods = this.getClass().getMethods();
		// for (int i = 0; i < methods.length; i++) {
		// if (methods[i].getName().equals(type)) {
		// try {
		// methods[i].invoke(this, request, response);
		// } catch (IllegalAccessException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IllegalArgumentException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// }
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
		}
	}

	// private void show(HttpServletRequest request, HttpServletResponse
	// response) {
	// // 刚进来的时候，可能ye为空，所以默认ye=1；
	// int ye = 1;// 要判断ye是否为空，默认ye=1
	// // 要保证最小页是第一页
	// if (null != request.getParameter("ye")) {
	// ye = Integer.parseInt(request.getParameter("ye"));
	// }
	// // 没什么参数，直接调Dao层
	// // stuDao = new StudentDao();
	// int pageNum = 2;// 一页显示多少条
	// int yeMa = 5;
	// // 显示有多少条数据
	// int total = stuDao.SearchCount();
	// // 否中
	// int maxYe = 0;
	// // if(total%pageNum==0){
	// // maxYe=total/pageNum;
	// // }else{
	// // maxYe=total/pageNum+1;
	// // }
	// // 变量=布尔表达式？表达式：表达式；
	// // 求出最大页之后也要给前台发过去
	// maxYe = total % pageNum == 0 ? total / pageNum : total / pageNum + 1;
	// // if (ye < 1) {
	// // ye = 1;
	// // }
	// // 要保证最大页是maxYe,后台控制不要从地址栏上输入，控制
	// // if (ye > maxYe) {
	// // ye = maxYe;
	// // }
	// // 这个begin是变动的，因为可能第二页，第三页是开始页；
	// Pagination pagination = new Pagination(ye, total, pageNum, yeMa);
	//
	//
	// int begin = pageNum * (ye - 1);
	// // 数据库limit的两个参数代表什么？？？？？？？？？？？？？？？
	// // 调Dao层的数据放在list里面
	// List<Student> list = stuDao.SearchByBegin(begin, pageNum);
	// // 转发的时候给谁传过去？？？？？？？給jsp
	// request.setAttribute("stus", list);
	// System.out.println(pagination.getMaxYe());
	// request.setAttribute("p", pagination);
	// // 记录当前页，并把当前页传给jsp
	// // request.setAttribute("ye", ye);
	// // 要把maxYe传过去，jsp接收
	// // request.setAttribute("maxYe", maxYe);
	// try {
	// // 做一次转发
	// request.getRequestDispatcher("WEB-INF/student/list.jsp").forward(
	// request, response);
	// } catch (ServletException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// } catch (IOException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// }

	private void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			Student condition = new Student();
			String name = request.getParameter("name");
			if (!"".equals(name)) {
				condition.setName(name);
			}
			String sex = request.getParameter("sex");
			if ("男".equals(sex) || "女".equals(sex)) {
				condition.setSex(sex);

			}
			// System.out.println(sex);
			if (null == request.getParameter("age")
					|| "".equals(request.getParameter("age"))) {
				condition.setAge(-1);
			} else {
				int age = Integer.parseInt(request.getParameter("age"));
				condition.setAge(age);
				
			}
			int ye = 1;
			if (null != request.getParameter("ye")) {
				ye = Integer.parseInt(request.getParameter("ye"));

			}

			int pageNum = 2;
			int yeMa = 5;
			int total = stuDao.SearchCount(condition);
			int maxYe = 0;
			maxYe = total % pageNum == 0 ? total / pageNum : total / pageNum
					+ 1;
			Pagination pagination = new Pagination(ye, total, pageNum, yeMa);
			ye = pagination.getYe();
			int begin = pageNum * (ye - 1);
			List<Student> list = stuDao.searchByCondition(condition, begin,
					pageNum);
			request.setAttribute("p", pagination);
			request.setAttribute("condition", condition);
			request.setAttribute("stus", list);
			request.getRequestDispatcher("WEB-INF/student/list.jsp").forward(
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
			flag = stuDao.delete(Integer.parseInt(ids[i]));
			if (flag == false) {
				break;
			}
		}
		if (flag) {
			try {
				response.sendRedirect("stus");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// 拿参数
		String name = "";
		String sex = "";
		int age = 0;
		int bjId = 0;
		String newFileName="";
		// 上传图片
		FileItemFactory factory = new DiskFileItemFactory();
		// FileItemFactory 这个类用来组织所有文件，通过统一的接口访问文件的信息。
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合
		// 每一个FileItem对应一个Form表单的输入项
		List<FileItem> items;
		try {
			items = upload.parseRequest(request);
			String url = request.getServletContext().getRealPath("/")
					+ "/photos/";
			for (int i = 0; i < items.size(); i++) {
				//!"".equals(request.getParameter("sex")
				//!"".equals(request.getParameter("bj")
				//|| null != request.getParameter("age"))
				if ("name".equals(items.get(i).getFieldName())) {
					name = new String(items.get(i).getString()
							.getBytes("ISO-8859-1"), "utf-8");
				} else if ("sex".equals(items.get(i).getFieldName())) {
					sex = new String(items.get(i).getString()
							.getBytes("ISO-8859-1"), "utf-8");
				} else if ("age".equals(items.get(i).getString())){
					age = Integer.parseInt(items.get(i).getString());
				} else if ("bj".equals(items.get(i).getString())) {
					bjId = Integer.parseInt(items.get(i).getString());
				} else if ("photo".equals(items.get(i).getFieldName())) {
					UUID uuid=UUID.randomUUID();
					String oldFileName=items.get(i).getName();
					String houzhui=oldFileName.substring(oldFileName.lastIndexOf("."));
					newFileName=uuid+houzhui;
					File file = new File(url + "/" + newFileName);
					try {
						items.get(i).write(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			Student stu = new Student();
			stu.setName(name);
			stu.setSex(sex);
			stu.setAge(age);
			BanJi bj = new BanJi();
			bj.setBanji_id(bjId);
			stu.setBj(bj);
			stu.setPhoto(newFileName);
			// 把拿到的数据通过Dao层传给数据库
			boolean flag = stuDao.add(stu);
			// 这里保存成功或是失败要给个提示
			if (flag) {
				// request.getRequestDispatcher("stus").forward(request,response);
				response.sendRedirect("stus");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			BanJiDao bjDao = new BanJiDao();
			List<BanJi> list = bjDao.SearchAll();
			request.setAttribute("bjs", list);
			request.getRequestDispatcher("WEB-INF/student/add.jsp").forward(
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
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			Student stu = new Student();
			stu.setId(id);
			stu.setName(name);
			stu.setSex(sex);
			stu.setAge(age);

			boolean flag = stuDao.modify(stu);
			if (flag) {
				response.sendRedirect("stus");
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
			// 从Dao层里得到stu
			Student stu = new Student();
			stu = stuDao.SearchById(id);
			// System.out.println(stu + "1stu.getName99");
			String name = stu.getName();
			// System.out.println(stu.getSex() + "2stu.getSex99");
			request.setAttribute("stus", stu);
			request.getRequestDispatcher("WEB-INF/student/modify.jsp").forward(
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
