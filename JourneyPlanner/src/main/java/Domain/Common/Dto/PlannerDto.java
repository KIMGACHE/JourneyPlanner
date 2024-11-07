package Domain.Common.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PlannerDto {
	private int plannerid;
	private int areacode;
	private int citycode;
	private LocalDate startdate;
	private LocalDate enddate;
	
	public PlannerDto() {}
	
	public PlannerDto(int plannerid, int areacode, int citycode, LocalDate startdate, LocalDate enddate) {
		super();
		this.plannerid = plannerid;
		this.areacode = areacode;
		this.citycode = citycode;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public int getPlannerid() {
		return plannerid;
	}

	public void setPlannerid(int plannerid) {
		this.plannerid = plannerid;
	}

	public int getAreacode() {
		return areacode;
	}

	public void setAreacode(int areacode) {
		this.areacode = areacode;
	}

	public int getCitycode() {
		return citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	@Override
	public String toString() {
		return "PlannerDto [plannerid=" + plannerid + ", areacode=" + areacode + ", citycode=" + citycode
				+ ", startdate=" + startdate + ", enddate=" + enddate + "]";
	}
}
