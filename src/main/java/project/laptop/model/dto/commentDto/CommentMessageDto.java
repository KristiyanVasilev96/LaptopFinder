package project.laptop.model.dto.commentDto;

public class CommentMessageDto {
    private String message;

    public CommentMessageDto() {
    }

    public String getMessage() {
        return message;
    }

    public CommentMessageDto setMessage(String message) {
        this.message = message;
        return this;
    }
}
