package com.adam.api.response;

public class APIResponse {

    // 狀態值編碼
    private Integer code;

    // 成功訊息
    private String Msg = "";

    // 錯誤訊息
    private String errorMsg = "";

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
