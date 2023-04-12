package pdes.c1.universitypanel.model.dto;

public class CourseDTO {
	private int year;
	private int fourMonthPeriod;
	private String subject;

	public CourseDTO(int year, int fourMonthPeriod, String subject) {
		this.year = year;
		this.fourMonthPeriod = fourMonthPeriod;
		this.subject = subject;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getFourMonthPeriod() {
		return this.fourMonthPeriod;
	}

	public void setFourMonthPeriod(int fourMonthPeriod) {
		this.fourMonthPeriod = fourMonthPeriod;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
