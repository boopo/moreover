package com.lv.spring.exceptioin;

import com.lv.spring.enums.ResultVOEnum;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class ApiException extends RuntimeException {

    @ApiModelProperty(value = "code")
    private Integer code;

    public ApiException(String messgae, Integer code) {
        super(messgae);
        this.code = code;
    }

    public ApiException(ResultVOEnum resultVOEnum) {
        super(resultVOEnum.getMessage());
        this.code = resultVOEnum.getCode();
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
