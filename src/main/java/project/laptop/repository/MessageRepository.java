package project.laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.laptop.model.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity,Long> {
}
