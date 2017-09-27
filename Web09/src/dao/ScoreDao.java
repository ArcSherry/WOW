package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import entity.BanJi;
import entity.Score;
import entity.Student;
import entity.Subject;

public class ScoreDao extends BaseDao {
	public List<Score> SearchByCondition(Score condition, int begin, int pageNum) {
		List<Score> list = new ArrayList<Score>();
		getStatement();
		String where = " where 1=1";
		if (!"".equals(condition.getStu().getName())&&null!=condition.getStu().getName()) {
			where += " and name like'%" + condition.getStu().getName() + "%'";
		}
		if (condition.getStu().getBj().getBanji_id() != 0) {
			where += " and banji_id" + condition.getStu().getBj().getBanji_id()
					+ "";
		}
		if (condition.getSub().getId() != 0) {
			where += " and sub_id" + condition.getSub().getId() + "";
		}
		// 这里要用begin和pageNum,就要传值

		// 遍历
		try {
				rs = stat.executeQuery("select * from v_sc " + where + " limit "
					+ begin + "," + pageNum);

				
				while (rs.next()) {
				Score sc = new Score();
				sc.setId(rs.getInt("sc_id"));
				sc.setScore((Integer) rs.getObject("score"));
				sc.setGrade(rs.getString("grade"));
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				BanJi bj = new BanJi();
				bj.setBanji_id(rs.getInt("banji_id"));
				bj.setBanji_name(rs.getString("banji_name"));
				stu.setBj(bj);
				sc.setStu(stu);
				Subject sub = new Subject();
				sub.setId(rs.getInt("sub_id"));
				sub.setSub_name(rs.getString("sub_name"));
				sc.setSub(sub);
				list.add(sc);
			}
				System.out.println(list.size()+"sql语句");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	public int SearchCount(Score condition) {
		List<Score> list = new ArrayList<Score>();
		int count = 0;
		getStatement();
		String where = " where 1=1";
		if (!"".equals(condition.getStu().getName())&&null!=condition.getStu().getName()) {
			where += " and name like'%" + condition.getStu().getName() + "%'";
		}
		if (condition.getStu().getBj().getBanji_id() != 0) {
			where += " and banji_id" + condition.getStu().getBj().getBanji_id()
					+ "";
		}
		if (condition.getSub().getId() != 0) {
			where += " and sub_id" + condition.getSub().getId() + "";
		}
		// 这里要用begin和pageNum,就要传值
		try {
			rs = stat.executeQuery("select count(*) from v_sc  " + where);
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
			System.out.println(count+"总条目");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return count;
	}
}
