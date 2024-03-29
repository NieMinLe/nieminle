package com.swaggertest.demo.webApi;

/**
 * 用于封装返回到前端的数据、状态及消息
 * @author nie
 */
public class ApiResult {
	
	 private static final long serialVersionUID = -3948389268046368059L;

	    private Integer code;

	    private String msg;

	    private Object data;

	    private Integer count;

	    public ApiResult() {}

	    public ApiResult(Integer code, String msg,Integer count) {
	        this.code = code;
	        this.msg = msg;
	        this.count = count;
	    }

	    public static ApiResult success() {
	        ApiResult result = new ApiResult();
	        result.setResultCode(ResultCode.SUCCESS);
	        return result;
	    }

	public static ApiResult success(Object data) {
		ApiResult result = new ApiResult();
		result.setResultCode(ResultCode.SUCCESS);
		result.setData(data);
		return result;
	}

	    public static ApiResult success(Object data, Integer count) {
	        ApiResult result = new ApiResult();
	        result.setResultCode(ResultCode.SUCCESS);
	        result.setData(data);
	        result.setCount(count);
	        return result;
	    }

	    public static ApiResult failure(ResultCode resultCode, String msg, Boolean flag) {
	    	ApiResult result = new ApiResult();
	        result.setResultCode(resultCode);
	        result.setMsg(msg);
	        return result;
	    }



	    public static ApiResult failure(ResultCode resultCode, Object data) {
	    	ApiResult result = new ApiResult();
	        result.setResultCode(resultCode);
	        result.setData(data);
	        return result;
	    }

	    public void setResultCode(ResultCode code) {
	        this.code = code.code();
	        this.msg = code.message();
	    }

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
