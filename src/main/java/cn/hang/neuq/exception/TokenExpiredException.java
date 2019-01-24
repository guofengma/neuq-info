package cn.hang.neuq.exception;


/**
 * @author lihang15
 */
public class TokenExpiredException extends RuntimeException {

    private String msg;

    public TokenExpiredException() {
        super();
    }
    public TokenExpiredException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
