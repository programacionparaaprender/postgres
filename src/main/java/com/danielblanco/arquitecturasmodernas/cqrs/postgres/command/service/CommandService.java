package com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.service;

import com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.model.CommentCommand;
import com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.model.PostCommand;
import com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.model.ReactionCommand;
import com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.repository.CommentCommandRepository;
import com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.repository.PostCommandRepository;
import com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.repository.ReactionCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommandService {

  @Autowired
  PostCommandRepository postRepository;

  @Autowired
  CommentCommandRepository commentRepository;

  @Autowired
  ReactionCommandRepository reactionRepository;

  @Transactional
  public PostCommand addPost(PostCommand post) {
	PostCommand postAdd = postRepository.save(post);
	return postAdd;
  }

  @Transactional
  public CommentCommand addComment(Long postId, CommentCommand comment) {
    comment.setPostId(postId);
    return commentRepository.save(comment);
  }

  @Transactional
  public ReactionCommand addReaction(Long postId, Long commentId, ReactionCommand reaction) {
    reaction.setCommentId(commentId);
    return reactionRepository.save(reaction);
  }
}
