package com.blogapp.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogapp.entity.Post;
import com.blogapp.payloads.ApiResponse;
import com.blogapp.payloads.CategoryDto;
import com.blogapp.payloads.PostDto;
import com.blogapp.services.FileService;
import com.blogapp.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	// get by user
	@GetMapping("/user/{userId}/posts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDto> posts = this.postService.getPostsByUser(userId);

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get by category
	@GetMapping("/category/{categoryId}/posts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get all posts
	@GetMapping("/posts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<PostDto>> getAllPost() {
		List<PostDto> posts = this.postService.getAllPost();

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get post by Id
	@GetMapping("/posts/{postId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> getAllPost(@PathVariable Integer postId) {
		PostDto post = this.postService.getPostByID(postId);

		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}

	// delete post by Id
	@DeleteMapping("posts/{postId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is deleted successfully", true), HttpStatus.OK);
	}

	// update post
	@PutMapping("posts/{postId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
			@PathVariable Integer postId) {
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId
			) throws IOException{
		PostDto postDto = this.postService.getPostByID(postId);
		String fileName = this.fileService.uploadImage(path, image);
		//PostDto postDto = this.postService.getPostByID(postId);
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//method to serve files
	@GetMapping(value="post/image/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response
			)  throws IOException{
		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
