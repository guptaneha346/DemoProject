package Demo.response;

import Demo.model.Employee;

public class ValidationErrorResponse extends Response{

  public ValidationErrorResponse(String field , String msg) {
    super(null,400,msg);
  }
}
