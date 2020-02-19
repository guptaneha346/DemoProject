package Demo.response;

public class Response<T> {
  private T result;
  private String message;
  private Integer code;

  public Response(T result,int code,String message) {
    this.result=result;
    this.code=code;
    this.message=message;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
