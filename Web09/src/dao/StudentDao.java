package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import entity.BanJi;
import entity.Student;

public class StudentDao extends BaseDao {
	Student stu;
	ResultSet rs;
	// 定义一个数组，把拿到的stu放到list里
	List<Student> list;

	// public List<Student> SearchAll() {
	// list = new ArrayList<Student>();
	// getStatement();
	// try {
	// rs = stat.executeQuery("select * from student");
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// try {
	// while (rs.next()) {
	// Student stu = new Student();
	// stu.setId(rs.getInt("id"));
	// stu.setName(rs.getString("name"));
	// stu.setSex(rs.getString("sex"));
	// stu.setAge(rs.getInt("age"));
	// list.add(stu);
	//
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// closeAll();
	// }
	// return list;
	//
	// }

	public List<Student> SearchByBegin(int begin, int pageNum) {
		list = new ArrayList<Student>();
		try {
			getStatement();
			rs = stat.executeQuery("select * from student  " + begin + ","
					+ pageNum);
			// pageNum一页显示多少条
			while (rs.next()) {
				stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				list.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	public int SearchCount(Student condition) {
		int count = 0;
		try {
			getStatement();
			String where = " where 1=1";
			if (condition.getName() != null) {
				where += " and name like'%" + condition.getName() + "%'";
			}
			if (condition.getSex() != null) {
				where += " and sex='" + condition.getSex() + "'";
			}
			if (condition.getAge() != -1) {
				where += " and age=" + condition.getAge() + "";
			}
			// 记录id的个数
			rs = stat.executeQuery("select count(id) from student " + where);
			// rs = stat.executeQuery("select * from student " + where);

			if (rs.next()) {
				// count = rs.getInt(1);
				// 就是取你结果集的第一列。
				count = rs.getInt("count(id)");
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return count;
	}

	public boolean delete(int id) {
		boolean flag = false;
		try {
			String sql = "delete from student where id=?";
			getPreparedStatement(sql);

			pstat.setInt(1, id);
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return flag;
	}

	public boolean add(Student stu) {
		boolean flag = false;
		// 往数据库里添加数据
		try {
			String sql = "insert into student (name,sex,age,bj_id,photo) "
					+ " values(?,?,?,?,?)";
			getPreparedStatement(sql);
			pstat.setString(1, stu.getName());
			pstat.setString(2, stu.getSex());
			pstat.setInt(3, stu.getAge());
			pstat.setInt(4, stu.getBj().getBanji_id());
			pstat.setString(5, stu.getPhoto());
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return flag;
	}

	public Student SearchById(int id) {
		Student stu = new Student();
		getStatement();
		try {
			rs = stat.executeQuery("select * from student where id=" + id);
			if (rs.next()) {
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				// System.out.println(rs.getInt("id") + "byidid");
				// System.out.println(rs.getString("name")+"byname");
				stu.setSex(rs.getString("sex"));
				// System.out.println(rs.getString("sex") + "bysexsex");
				stu.setAge(rs.getInt("age"));
				// System.out.println(rs.getInt("age") + "byageage");
			}
			// System.out.println(stu.getName()+ "byname");
			// 这个应该把stu放在if的外面，不然stu只能作用于if里面
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return stu;
	}

	public boolean modify(Student stu) {
		boolean flag = false;
		getPreparedStatement("update student set name=?,sex=?,age=? where id=?");
		try {
			pstat.setString(1, stu.getName());
			pstat.setString(2, stu.getSex());
			pstat.setInt(3, stu.getAge());
			pstat.setInt(4, stu.getId());
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return flag;
	}

	public List<Student> searchByCondition(Student condition, int begin,
			int pageNum) {
		list = new ArrayList<Student>();
		try {
			getStatement();
			String where = " where 1=1";
			if (condition.getName() != null) {
				where += " and name like '%" + condition.getName() + "%'";
			}
			// System.out.println(condition.getSex() + "condition.getSex()");
			if (condition.getSex() != null) {
				where += " and sex='" + condition.getSex() + "'";
			}
			if (condition.getAge() != -1) {
				where += " and age=" + condition.getAge() + "";
			}
			rs = stat
					.executeQuery("select * from student left join banji on bj_id=banji_id"
							+ where + " limit " + begin + "," + pageNum);

			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				stu.setPhoto(rs.getString("photo"));
				BanJi bj = new BanJi();
				bj.setBanji_id(rs.getInt("banji_id"));
				bj.setBanji_name(rs.getString("banji_name"));
				stu.setBj(bj);
				list.add(stu);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

}
