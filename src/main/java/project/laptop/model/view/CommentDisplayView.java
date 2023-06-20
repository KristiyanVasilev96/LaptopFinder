package project.laptop.model.view;

public class CommentDisplayView {

    private Long id;

    private String authorName;
    private String message;


    public CommentDisplayView(String authorName, String message, Long id) {
        this.authorName = authorName;
        this.message = message;
        this.id = id;
    }


    public String getAuthorName() {
        return authorName;
    }

    public CommentDisplayView setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentDisplayView setMessage(String message) {
        this.message = message;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CommentDisplayView setId(Long id) {
        this.id = id;
        return this;
    }
}
