package store.constants;

public enum ErrorMessages {
    PATTERN("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.")
    ;

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR] " + message;
    }
}
