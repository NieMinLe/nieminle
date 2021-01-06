package com.swaggertest.demo.system.enums;

/**
 * 审核状态
 **/
public enum EnumApplyStatus {
    //（1待审批、2审批中、3审批通过、4审批不通过、9已撤销）
    /**
     * 待审批
     */
    WAIT_APPROVAL(1, "待审批"),
    /**
     * 审批中
     */
    APPROVAL_IN(2, "审批中"),
    /**
     * 审批通过
     */
    APPROVAL_PASS(3, "审批通过"),
    /**
     * 审批不通过
     */
    APPROVAL_NOT_PASS(4, "审批不通过"),
    /**
     * 已撤销
     */
    RESCINDED(9, "已撤销"),

    ;

    public static boolean isRefund(Byte code) {
        if (code == null) {
            return Boolean.FALSE;
        } else {
            return RESCINDED.getStatus().byteValue() == code ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    /**
     * 状态码
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String desc;

    EnumApplyStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据传入的数值查询对应的枚举值.
     *
     * @param status 被查询数值.
     * @return 被查询数值是正确的枚举值时，返回对应的枚举值，否则返回null.
     */
    public static EnumApplyStatus valueOfCode(Integer status) {
        for (EnumApplyStatus item : EnumApplyStatus.values()) {
            if (item.getStatus().equals(status)) {
                return item;
            }
        }
        return null;
    }

    public static String getName(Integer status){
        EnumApplyStatus enumApplyStatus = valueOfCode(status);
        return enumApplyStatus == null ? "":enumApplyStatus.getDesc();
    }

}
