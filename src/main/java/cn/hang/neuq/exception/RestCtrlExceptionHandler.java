package cn.hang.neuq.exception;

import cn.hang.neuq.common.Response;
import cn.hang.neuq.common.ResponseMessageEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lihang15
 */
@Slf4j
@RestControllerAdvice
public class RestCtrlExceptionHandler {


    @ExceptionHandler(InfoException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Response<Object> handleInfoException(InfoException e) {
        if (e != null) {
            log.error("handleInfoException", e);
            if (StringUtils.isNotBlank(e.getMessage())) {
                return Response.error(e.getMessage());
            }
        }
        return Response.error();
    }

    @ExceptionHandler(TokenInvalidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Response<Object> handleTokenInvalidException(TokenInvalidException e) {
        return Response.error(ResponseMessageEnum.ACCESS_TOKEN_INVALID);
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Response<Object> handleTokenExpiredException(TokenExpiredException e) {
        return Response.error(ResponseMessageEnum.ACCESS_TOKEN_NOT_EXIST);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Response<Object> handleException(Exception e) {
        if (e != null) {
            log.error("handleInfoException", e);
        }
        return Response.error();
    }
}
