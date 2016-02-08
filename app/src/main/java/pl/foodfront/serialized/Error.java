package pl.foodfront.serialized;

import java.io.Serializable;

/**
 * Created by Michał Stobiński on 2016-01-28.
 */
public class Error implements Serializable {

    private Integer errno;
    private String error;

    public Integer getErrno() { return errno; }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
