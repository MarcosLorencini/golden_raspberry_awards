package com.golden.raspberry.awards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AwardMinMaxDto {

	@JsonProperty("min")
    private List<AwardMinDto> awardMinDtoList;

	@JsonProperty("max")
    private List<AwardMaxDto> awardMaxDtoList;


	public List<AwardMinDto> getAwardMinList() {
		return awardMinDtoList;
	}

	public void setAwardMinList(List<AwardMinDto> awardMinDtoList) {
		this.awardMinDtoList = awardMinDtoList;
	}

	public List<AwardMaxDto> getAwardMaxList() {
		return awardMaxDtoList;
	}

	public void setAwardMaxList(List<AwardMaxDto> awardMaxDtoList) {
		this.awardMaxDtoList = awardMaxDtoList;
	}
}