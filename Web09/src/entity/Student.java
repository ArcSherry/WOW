package entity;

import java.io.Serializable;

public class Student implements Serializable {
	private int id;
	private String name;
	private int age;
	private String sex;
	private String banji_name;
	private String photo;
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getBanji_name() {
		return banji_name;
	}
	public void setBanji_name(String banji_name) {
		this.banji_name = banji_name;
	}
	// 用不到的banjiid放在这里不可以？
	private BanJi bj;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public BanJi getBj() {
		return bj;
	}
	public void setBj(BanJi bj) {
		this.bj = bj;
	}
}