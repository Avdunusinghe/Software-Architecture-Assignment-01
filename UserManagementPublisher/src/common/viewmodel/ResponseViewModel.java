package common.viewmodel;

public class ResponseViewModel {
	
	public boolean isSuccess;
	public String message;
	
	public ResponseViewModel() {
		super();
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
