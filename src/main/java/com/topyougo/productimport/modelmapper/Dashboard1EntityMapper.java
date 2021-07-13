package com.topyougo.productimport.modelmapper;

import java.util.Calendar;

import org.modelmapper.ModelMapper;
import com.topyougo.productimport.dto.Dashboard1DTO;
import com.topyougo.productimport.dto.Dashboard2DTO;
import com.topyougo.productimport.model.Dashboard1;
import com.topyougo.productimport.model.Dashboard2;
import com.topyougo.productimport.util.DateUtil;

public class Dashboard1EntityMapper {

	private static ModelMapper modelMapper = new ModelMapper();

	public static Dashboard1DTO convertModelToDTO(Dashboard1 model) {
		Dashboard1DTO dto = modelMapper.map(model, Dashboard1DTO.class);
		dto.setCurrentDate(DateUtil.fortmatDateToString(Calendar.getInstance().getTime()));
		return dto;
	}

	public static Dashboard2DTO convertModelToDTO(Dashboard2 model) {
		Dashboard2DTO dto = modelMapper.map(model, Dashboard2DTO.class);
		return dto;
	}
}
