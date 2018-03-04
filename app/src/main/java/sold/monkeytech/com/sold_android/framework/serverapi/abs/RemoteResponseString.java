package sold.monkeytech.com.sold_android.framework.serverapi.abs;

/**
 * Created by eli on 17/06/2014.
 */
public class RemoteResponseString {
    boolean isSuccess=false;
    int status = 0;
    String message="";

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    String errorMessage="";

    public RemoteResponseString(Boolean isSuccess, String message , int status) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.status = status;
        this.errorMessage = errorMessage;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
