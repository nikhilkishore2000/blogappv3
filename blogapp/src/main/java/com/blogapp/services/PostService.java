package com.blogapp.services;

import java.util.List;

import com.blogapp.entity.Post;
import com.blogapp.payloads.PostDto;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto,Integer userID,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts
	List<PostDto> getAllPost();
	
	//get post
	PostDto getPostByID(Integer postId);
	
	//get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search
	List<Post> searchPosts(String Keyword);

}
