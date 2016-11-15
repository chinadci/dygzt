package software.dygzt.service.user.model;

import java.io.Serializable;


public class UserCheckResult implements Serializable{

	private static final long serialVersionUID = 530560250721561545L;

	boolean isSuccess;
	
	ResultCodeEnum resultCode;
	
	UserContextModel xtyh;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public ResultCodeEnum getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCodeEnum resultCode) {
		this.resultCode = resultCode;
	}

	public UserContextModel getXtyh() {
		return xtyh;
	}

	public void setXtyh(UserContextModel xtyh) {
		this.xtyh = xtyh;
	}

}
