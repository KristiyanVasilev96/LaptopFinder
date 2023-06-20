package project.laptop.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import project.laptop.model.dto.commentDto.CommentCreationDto;
import project.laptop.model.dto.commentDto.CommentMessageDto;
import project.laptop.model.entity.CommentEntity;
import project.laptop.model.entity.view.CommentView;
import project.laptop.model.view.CommentDisplayView;
import project.laptop.service.CommentService;
import project.laptop.service.UserService;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")

public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }


    @GetMapping("/{offerId}/comments")
    public ResponseEntity<List<CommentDisplayView>> getComments(@PathVariable("offerId") Long offerId) {
        return ResponseEntity.ok(commentService.getAllCommentsForOffer(offerId));

    }


    @PostMapping(value = "/{offerId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentDisplayView> createComment(@PathVariable("offerId") Long offerId,
                                                            @AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody CommentMessageDto commentDto) {


        CommentCreationDto commentCreationDto = new CommentCreationDto(
                userDetails.getUsername(),
                offerId,
                commentDto.getMessage()

        );
        CommentDisplayView comment = commentService.createComment(commentCreationDto);

        return ResponseEntity.created(URI.create(String.format("/api/%d/comments/%d", offerId, comment.getId())))
                .body(comment);
    }


}
