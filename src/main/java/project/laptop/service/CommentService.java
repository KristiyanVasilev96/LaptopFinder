package project.laptop.service;

import org.springframework.stereotype.Service;
import project.laptop.exceptions.ObjectNotFoundException;
import project.laptop.model.dto.commentDto.CommentCreationDto;
import project.laptop.model.entity.CommentEntity;
import project.laptop.model.entity.OfferEntity;

import project.laptop.model.view.CommentDisplayView;
import project.laptop.repository.CommentRepository;
import project.laptop.repository.OfferRepository;
import project.laptop.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentService(OfferRepository offerRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }


    public List<CommentDisplayView> getAllCommentsForOffer(Long offerId) {
        OfferEntity offer = offerRepository.findById(offerId).orElseThrow(() -> new ObjectNotFoundException("Not found"));

        List<CommentEntity> comments = commentRepository.findAllByOffer(offer).get();
        return
                comments.stream().map(comment -> new CommentDisplayView(comment.getText(), comment.getAuthor().getFirstName(), comment.getId()))
                        .collect(Collectors.toList());

    }

    public CommentDisplayView createComment(CommentCreationDto commentDto) {

        var author = userRepository.findByEmail(commentDto.getFirstName()).get();

        CommentEntity comment = new CommentEntity();
        comment.setCreated(LocalDateTime.now());
        comment.setOffer(offerRepository.findById(commentDto.getOfferId()).orElse(null));
        comment.setAuthor(author);
        comment.setApproved(true);
        comment.setText(commentDto.getMessage());


        commentRepository.save(comment);

        return new CommentDisplayView(author.getFirstName(), comment.getText(), comment.getId());
    }






}
