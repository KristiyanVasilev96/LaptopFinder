package project.laptop.model.dto.commentDto;

public class CommentCreationDto {
    private String firstName;

    private Long offerId;

    private String message;

    public CommentCreationDto(String firstName,  Long offerId, String text) {
        this.firstName = firstName;

        this.offerId = offerId;
        this.message = text;
    }

    public String getFirstName() {
        return firstName;
    }

    public CommentCreationDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }




    public Long getOfferId() {
        return offerId;
    }

    public CommentCreationDto setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentCreationDto setMessage(String message) {
        this.message = message;
        return this;
    }

}
