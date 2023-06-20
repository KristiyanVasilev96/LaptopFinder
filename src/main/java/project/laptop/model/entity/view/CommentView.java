package project.laptop.model.entity.view;

public class CommentView {
    private Long id;
    private String text;
    private String authorName;
    private String dateOfCreation;

    public CommentView(Long commentId, String text, String authorName, String dateOfCreation) {
        this.id = commentId;
        this.text = text;
        this.authorName = authorName;
        this.dateOfCreation = dateOfCreation;
    }

    public Long getId() {
        return id;
    }

    public CommentView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public CommentView setText(String text) {
        this.text = text;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public CommentView setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public CommentView setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
        return this;
    }
}
