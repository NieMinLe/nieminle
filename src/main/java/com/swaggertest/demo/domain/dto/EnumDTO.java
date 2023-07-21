package com.swaggertest.demo.domain.dto;

import lombok.Data;

/**
 * <p>
 * </p>
 *
 * @author nieminle
 * @since 2023-07-18
 */
@Data
public class EnumDTO {


    private String label;

    private String value;

    public EnumDTO(){

    }

    public EnumDTO(String label, String value){

        this.label=label;
        this.value=value;

    }

}
