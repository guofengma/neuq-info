package cn.hang.neuq.common;

/**
 * @author lihang15
 * @description
 * @create 2018-11-27 19:38
 **/
public interface ResponseMessageInterface {
    int getCode();

    String getMessage();

    public static class Default implements ResponseMessageInterface {
        private final int code;
        private final String message;

        Default(int code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }
}
