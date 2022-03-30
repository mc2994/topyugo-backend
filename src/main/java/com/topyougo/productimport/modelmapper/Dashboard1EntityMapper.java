package com.topyougo.productimport.modelmapper;

import java.util.Calendar;
import org.modelmapper.ModelMapper;
import com.topyougo.productimport.dto.Dashboard1DTO;
import com.topyougo.productimport.dto.Dashboard2DTO;
import com.topyougo.productimport.model.Dashboard1;
import com.topyougo.productimport.model.Dashboard2;
import com.topyougo.productimport.util.DateUtil;

/**
 * Filter base class that aims to guarantee a single execution per request
 * dispatch, on any servlet container. It provides a {@link #doFilterInternal}
 * method with HttpServletRequest and HttpServletResponse arguments.
 *
 * @author Mc Kinley Tolentino
 * @since 07.20.2021
 */
public final class Dashboard1EntityMapper {

    private Dashboard1EntityMapper() {

    }

    /**
     * Expose the classic Spring itself, if available, in particular for passing it
     * on to other consumers.
     */
    private static ModelMapper modelMapper = new ModelMapper();

    /**
     * Expose the classic Spring itself, if available, in particular for passing it
     * on to other consumers.
     * <p>
     * If sufficient for the purposes at hand is recommended over this variant.
     * @param model
     * 
     * @return ${Dashboard1DTO.java}
     * @since 5.0.3
     */
    public static Dashboard1DTO convertModelToDTO(final Dashboard1 model) {
	Dashboard1DTO dto = modelMapper.map(model, Dashboard1DTO.class);
	dto.setCurrentDate(DateUtil
		.fortmatDateToString(Calendar
			.getInstance()
			.getTime()));
	return dto;
    }

    /**
     * in particular for passing it on to other consumers.
     * <p>
     * If sufficient for the purposes at hand, is recommended over this variant.
     * 
     * @param model
     * 
     * @return ${Dashboard2DTO.java}
     * @since 5.0.3
     */
    public static Dashboard2DTO convertModelToDTO(final Dashboard2 model) {
	Dashboard2DTO dto = modelMapper.map(model, Dashboard2DTO.class);
	return dto;
    }
}
