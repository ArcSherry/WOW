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

public class BanJiDao extends BaseDao {

	BanJi bj;
	ResultSet rs;
	List<BanJi> list;

	public List<BanJi> SearchAll() {
		list = new ArrayList<BanJi>();
		getStatement();
		try {
			rs = stat.executeQuery("select * from banji");
			while (rs.next()) {
				BanJi bj = new BanJi();
				bj.setBanji_id(rs.getInt("banji_id"));
				bj.setBanji_name(rs.getString("banji_name"));
				bj.setStuNums(rs.getInt("stuNums"));
				list.add(bj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	public boolean delete(int id) {
		boolean flag = false;
		try {
			getPreparedStatement("delete from banji where banji_id=?");
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

	public List<BanJi> SearchByBegin(int begin, int pageNum) {
		list = new ArrayList<BanJi>();
		try {
			getStatement();
			rs = stat.executeQuery("select * from banji limit " + begin + ","
					+ pageNum);
			while (rs.next()) {
				bj = new BanJi();
				bj.setBanji_id(rs.getInt("banji_id"));
				bj.setBanji_name(rs.getString("banji_name"));
				bj.setStuNums(rs.getInt("stuNums"));
				list.add(bj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	public boolean add(BanJi bj) {
		boolean flag = false;
		// 往数据库里添加数据
		try {
			getPreparedStatement("insert into banji (banji_name,stuNums) "
					+ " values(?,?)");
			pstat.setString(1, bj.getBanji_name());
			pstat.setInt(2, bj.getStuNums());
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

	public BanJi SearchById(int id) {
		BanJi bj = new BanJi();
		getStatement();
		try {
			rs = stat.executeQuery("select * from banji where banji_id=" + id);
			if (rs.next()) {
				bj.setBanji_id(rs.getInt("banji_id"));
				bj.setBanji_name(rs.getString("banji_name"));
				bj.setStuNums(rs.getInt("stuNums"));
				list.add(bj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bj;
	}

	public BanJi SearchByBjAndSubById(int id) {
		BanJi bj = new BanJi();
		List<Subject> list = new ArrayList<Subject>();
		getStatement();
		try {
			rs = stat.executeQuery("select * from v_bj_sub where banji_id="
					+ id);
			int i = 0;
			while (rs.next()) {
				if (i == 0) {
					bj.setBanji_id(rs.getInt("banji_id"));
					System.out.println(rs.getInt("banji_id"));
					bj.setBanji_name(rs.getString("banji_name"));
					System.out.println(rs.getString("banji_name"));
				
				}
				Subject sub = new Subject();
				// 这里是subject.id,不是sub_id
				sub.setId(rs.getInt("id"));
				sub.setSub_name(rs.getString("sub_name"));
				list.add(sub);
				i++;
			}bj.setSubs(list);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bj;
	}

	public boolean modify(BanJi bj) {
		boolean flag = false;
		getPreparedStatement("update banji set banji_name=?,stuNums=? where banji_id=?");
		try {
			pstat.setString(1, bj.getBanji_name());
			pstat.setInt(2, bj.getStuNums());
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

	public List<BanJi> searchByCondition(BanJi condition, int begin, int pageNum) {
		list = new ArrayList<BanJi>();
		try {
			getStatement();
			String where = " where 1=1";
			
			if (condition.getBanji_name() != null) {
				where += " and banji_name='" + condition.getBanji_name() + "'";
			}
			if(condition.getBanji_id()!=0){
				where+=" and banji_id=" + condition.getBanji_id()+" ";
			}
			rs = stat.executeQuery("select * from banji " + where + " limit "
					+ begin + "," + pageNum);
			while (rs.next()) {
				bj = new BanJi();
				bj.setBanji_id(rs.getInt("banji_id"));
				bj.setBanji_name(rs.getString("banji_name"));
				bj.setStuNums(rs.getInt("stuNums"));
				list.add(bj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	public int SearchCount(BanJi condition) {
		int count = 0;
		try {
			getStatement();
			rs = stat.executeQuery("select count(id) as c from score");
			if (rs.next()) {
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return count;
	}
}
