package com.lv.spring.vo;

import com.lv.spring.enums.ResultVOEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "全局统一响应")
public class ResultVO<T> {
    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public ResultVO(){}

    protected static <T> ResultVO<T> build(T data) {
        ResultVO<T> result = new ResultVO<T>();
        if (data != null)
            result.setData(data);
        return result;
    }

    public static <T> ResultVO<T> build(T body, ResultVOEnum resultVOEnum) {
        ResultVO<T> result = build(body);
        result.setCode(resultVOEnum.getCode());
        result.setMessage(resultVOEnum.getMessage());
        return result;
    }

    public static <T> ResultVO<T> build(Integer code, String message) {
        ResultVO<T> ResultVO = build(null);
        ResultVO.setCode(code);
        ResultVO.setMessage(message);
        return ResultVO;
    }

    public static<T> ResultVO<T> ok(){
        return ResultVO.ok(null);
    }

    /**
     * 操作成功
     * @param data
     * @param <T>
     * @return
     */
    public static<T> ResultVO<T> ok(T data){
        ResultVO<T> result = build(data);
        return build(data, ResultVOEnum.SUCCESS);
    }

    public static<T> ResultVO<T> fail(){
        return ResultVO.fail(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> ResultVO<T> fail(T data){
        ResultVO<T> result = build(data);
        return build(data, ResultVOEnum.FAIL);
    }

    public ResultVO<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public ResultVO<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    public boolean isOk() {
        if(this.getCode().intValue() == ResultVOEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }

}
