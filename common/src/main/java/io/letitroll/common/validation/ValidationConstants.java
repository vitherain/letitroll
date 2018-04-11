package io.letitroll.common.validation;

public final class ValidationConstants {

    public static final String IP_ADDRESS_REGEX = "/^(\\d{1,3}\\.){3}\\d{1,3}$/";
    public static final String EMAIL_REGEX = "/^[A-Za-z0-9._%+]+@[A-Za-z0-9.]+\\.[A-Za-z]{2,4}$/";

    private ValidationConstants() {
    }
}
