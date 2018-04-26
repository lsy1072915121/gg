package com.liushiyao.gg.com;

//接口返回对象
public class BusiResult<T> {

  //返回错误码
  private Integer code;
  //返回信息
  private String message;
  //返回对象
  private T data;


  public BusiResult( BusiStatus busiStatus, T data){
    this.data = data;
    this.code = busiStatus.value ();
    this.message = busiStatus.getReasonPhrase ();

  }
  public BusiResult(BusiStatus busiStatus){
    this(busiStatus,null);
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
