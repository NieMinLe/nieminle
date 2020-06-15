package com.swaggertest.demo.system.enums;

import lombok.Getter;

@Getter
public enum EnumDataOpenCode  {

    SUCCESS("DOPEN000000", "000000"),

    NO_PERMISSION("DOPEN0001", "no permission"),

    PARAM_VALUE_ERROR("DOPEN0002", "param value error"),

    DATE_RANGE_ERROR("DOPEN0003", "date range error"),

    UNEXPECTED_SYSTEM_EXCEPTION("DOPEN0004", "unexpected system exception"),

    DELETE_FAIL("DOPEN0005", "delete data fail"),

    UPDATE_FAIL("DOPEN0006", "update data fail"),

    SELECT_FAIL("DOPEN0007", "select data fail"),


    PARAM_FLOW_ERROR("DOPEN1008", "param flow exception"),

    FAIL("DOPEN9999", "fail"),

    ;

    private String code;

    private String msg;

    EnumDataOpenCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
