package com.salesken.studentsReportingSystem;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "s1")
public class Semester1 extends Student{
	@Column	
private Integer english;
	@Column
private Integer maths;
	@Column
private Integer science;

public Integer getEnglish() {
	return english;
}
public void setEnglish(Integer english) {
	this.english = english;
}
public Integer getMaths() {
	return maths;
}
public void setMaths(Integer maths) {
	this.maths = maths;
}
public Integer getScience() {
	return science;
}
public void setScience(Integer science) {
	this.science = science;
}

}


