package game;

public enum Colour {
	RED("\033[0;31m"),
	BLUE("\033[0;34m"),
	RESET("\033[0m");
	
	private final String code;

    Colour(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}

