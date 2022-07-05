package us.hyalen.springtemplate.config;

import us.hyalen.springtemplate.core.dto.ErrorCode;

public enum InternalClientErrorType implements ErrorCode {
    CONNECTION_FAIL;

    @Override
    public String getKey() {
        return this.name();
    }
}
