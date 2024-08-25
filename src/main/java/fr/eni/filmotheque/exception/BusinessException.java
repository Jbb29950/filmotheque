package fr.eni.filmotheque.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> messages;

    public BusinessException() {
        this.messages = new ArrayList<>();
    }

    public List<String> getMessages() {
        return messages;
    }

    public void add(String message) {
        messages.add(message);
    }
}

