package entity;

public class Score {
	private int id;
	private Integer score;
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	private String grade;
	private Subject sub;
	private Student stu;
	public Subject getSub() {
		return sub;
	}
	public void setSub(Subject sub) {
		this.sub = sub;
	}
	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	
}
