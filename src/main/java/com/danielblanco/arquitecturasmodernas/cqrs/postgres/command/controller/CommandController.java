package com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.controller;

import com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.model.CommentCommand;
import com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.model.PostCommand;
import com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.model.ReactionCommand;
import com.danielblanco.arquitecturasmodernas.cqrs.postgres.command.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Controller
public class CommandController {

  @Autowired
  private CommandService commandService;

  @PostMapping("/new")
  public PostCommand addPost(@RequestBody PostCommand post) {
    PostCommand postAdd = null;
	try {
    	postAdd = commandService.addPost(post);
    }catch(Exception ex) {
    	postAdd = new PostCommand();
    	postAdd.setId(0L);
    	postAdd.setContent(ex.getMessage());
    	return postAdd;
    }
    return postAdd;
  }

  @PostMapping("/{postId}/comment")
  public CommentCommand addComment(@PathVariable Long postId, @RequestBody CommentCommand comment) {
    return commandService.addComment(postId, comment);
  }

  @PostMapping("/{postId}/comment/{commentId}/reaction")
  public ReactionCommand addReaction(@PathVariable Long postId, @PathVariable Long commentId,
      @RequestBody ReactionCommand reaction) {
    return commandService.addReaction(postId, commentId, reaction);
  }
}
