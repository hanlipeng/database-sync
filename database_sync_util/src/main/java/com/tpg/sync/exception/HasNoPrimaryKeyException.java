package com.tpg.sync.exception;

/**
 * Create by reph on 2017/10/12
 * @author reph
 */
public class HasNoPrimaryKeyException extends RuntimeException {
    private static final long serialVersionUID = 6341349154139341208L;

    public HasNoPrimaryKeyException(String msg) {
        super(msg);
    }
}
