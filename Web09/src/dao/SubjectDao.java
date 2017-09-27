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
import entity.Subject;

public class SubjectDao extends BaseDao {
	Subject sub;
	ResultSet rs;
	// ����һ�����飬���õ���sub�ŵ�list��
	List<Subject> list;

	public boolean delete(int id) {
		boolean flag = false;
		try {
			String sql = "delete from subject where id=?";
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

	public List<Subject> SearchAll() {
		list = new ArrayList<Subject>();
		getStatement();
		try {
			rs = stat.executeQuery("select * from subject");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setSub_name(rs.getString("sub_name"));
				list.add(sub);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	// ����������鲻������༶�Ŀγ̵�
	public List<Subject> SearchNoSubByBjId(int bjId) {
		list = new ArrayList<Subject>();
		getStatement();
		try {
			// ����ǲ�ѯ��䣬��ѯ���������Ŀγ�
			// ǰ���BanJiServlet��ȡ����鵽��ֵ
			rs = stat
					.executeQuery("select * from subject where id not in (select id from v_bj_sub where banji_id="
							+ bjId + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setSub_name(rs.getString("sub_name"));
				list.add(sub);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;

	}

	public List<Subject> SearchByBegin(int begin, int pageNum) {
		list = new ArrayList<Subject>();
		try {
			// left jion banji on bj_id=banji_id
			getStatement();
			rs = stat.executeQuery("select * from subject  " + begin + ","
					+ pageNum);
			while (rs.next()) {
				sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setSub_name(rs.getString("sub_name"));
				list.add(sub);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			closeAll();
		}
		return list;

	}

	public int SearchCount(Subject condition) {
		int count = 0;
		try {
			getStatement();
			String where = " where 1=1";
			if (condition.getSub_name() != null) {
				where += " and name=" + condition.getSub_name() + "";
			}
			rs = stat.executeQuery("select count(id) from subject " + where);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeAll();
		}
		return count;

	}

	public boolean add(Subject sub) {
		boolean flag = false;
		// �����ݿ����������
		try {
			getPreparedStatement("insert into subject (sub_name) "
					+ " values(?)");
			pstat.setString(1, sub.getSub_name());
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

	public Subject SearchById(int id) {
		Subject sub = new Subject();
		getStatement();
		try {
			rs = stat.executeQuery("select * from subject where id=" + id);
			if (rs.next()) {
				sub.setId(rs.getInt("id"));
				sub.setSub_name(rs.getString("sub_name"));
			}
			// ���Ӧ�ð�sub����if�����棬��Ȼsubֻ��������if����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return sub;
	}

	public boolean modify(Subject sub) {
		boolean flag = false;
		getPreparedStatement("update subject set name=? where id=?");
		try {
			pstat.setString(1, sub.getSub_name());
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

	public List<Subject> searchByCondition(Subject condition, int begin,
			int pageNum) {
		list = new ArrayList<Subject>();
		try {
			getStatement();
			String where = " where 1=1";
			if (condition.getSub_name() != null) {
				// where += " and name " + condition.getSub_name() + "";
				where += " and name like '%" + condition.getSub_name() + "%'";
			}
			rs = stat.executeQuery("select * from subject " + where + " limit "
					+ begin + "," + pageNum);

			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setSub_name(rs.getString("sub_name"));
				list.add(sub);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	public List<Subject> searchByCondition(int id) {
		list = new ArrayList<Subject>();
		try {
			getStatement();

			rs = stat
					.executeQuery("select * from relative join subject on banjiID="
							+ id + " and subID=id");

			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setSub_name(rs.getString("sub_name"));
				list.add(sub);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	// �����������ΪsubjectServletҪͨ������������Ӧ�Ŀ�Ŀ�����Դ�����
	public List<Subject> searchByBjId(int bjId) {
		list = new ArrayList<Subject>();
		try {
			getStatement();
			if(bjId==0){
				rs = stat.executeQuery("select subject.id as id, sub_name from subject");
			}else{
			rs = stat.executeQuery("select subject.id as id, sub_name from subject"
					+ " inner join relative on relative.subID=subject.id"
					+ " inner join banji on relative.banjiID=banji_id"
					+ " where relative.banjiID=" + bjId);
			}
			// Tip:ƴ��һ��Ҫ�пո�
			// Tip:�������������ѯ����������ͼ
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setSub_name(rs.getString("sub_name"));
				list.add(sub);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}
}
