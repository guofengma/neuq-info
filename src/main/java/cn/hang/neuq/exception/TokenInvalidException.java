package cn.hang.neuq.exception;


/**
 * @author lihang15
 */
public class TokenInvalidException extends RuntimeException {

    private String msg;

    public TokenInvalidException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
