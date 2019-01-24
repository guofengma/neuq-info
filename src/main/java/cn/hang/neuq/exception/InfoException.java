package cn.hang.neuq.exception;


/**
 * @author lihang15
 */
public class InfoException extends RuntimeException {

    private String msg;

    public InfoException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
