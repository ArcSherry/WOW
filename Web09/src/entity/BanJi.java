package entity;

import java.util.List;

public class BanJi {
	int banji_id;
	String banji_name;
	int stuNums;
	List<Subject> subs;

	public List<Subject> getSubs() {
		return subs;
	}

	public void setSubs(List<Subject> subs) {
		this.subs = subs;
	}

	public int getBanji_id() {
		return banji_id;
	}

	public void setBanji_id(int banji_id) {
		this.banji_id = banji_id;
	}

	public String getBanji_name() {
		return banji_name;
	}

	public void setBanji_name(String banji_name) {
		this.banji_name = banji_name;
	}

	public int getStuNums() {
		return stuNums;
	}

	public void setStuNums(int stuNums) {
		this.stuNums = stuNums;
	}

}