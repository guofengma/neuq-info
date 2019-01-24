package cn.hang.neuq.exception;


/**
 * @author lihang15
 */
public class JwNotAuthException extends RuntimeException {

    private String msg;

    public JwNotAuthException() {
        super();
    }
    public JwNotAuthException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
