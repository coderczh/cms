package com.coderczh.cms.resp;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * 账户端返回结果
 *
 * @author Charles
 * @since 2024-09-05 13:45:15
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private T message;

    /**
     * 返回时间戳
     */
    private Long timestamp;

    /**
     * 无参构造器
     */
    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功
     *
     * @param <T> 数据类型
     * @return 成功的返回报文
     */
    public static <T> ResultData<T> success(T message) {
        ResultData<T> resultData = new ResultData<>();
        return resultData.setCode(HttpStatus.OK.value())
                .setMessage(message);
    }

    /**
     * 失败
     *
     * @param code    返回代码
     * @param <T>     数据类型
     * @return 失败的返回报文
     */
    public static <T> ResultData<T> fail(int code) {
        ResultData<T> resultData = new ResultData<>();
        return resultData.setCode(code);
    }
}
