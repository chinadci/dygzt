package software.dygzt.service.user.model;

import software.dygzt.util.StringUtil;

/**
 * 登陆错误码Enum
 */
public enum ResultCodeEnum {

	ILLEGAL_USERNAME("ILLEGAL_USERNAME","用户名不存在"),
	ILLEGAL_PASSWORD("ILLEGAL_PASSWORD","密码错误"),
	EMPTY_USERSYSTEM("EMPTY_USERSYSTEM","该用户没有配置系统")
	;

	String code;
	String message;

	/**
	 * @param code
	 * @param message
	 * @return
	 */
	ResultCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static ResultCodeEnum getEnumByCode(String code){
		for(ResultCodeEnum result:ResultCodeEnum.values()){
			if(StringUtil.equals(result.getCode(), code)){
				return result;
			}
		}
		return null;
	}
}
