package com.example.hw27.Controller;

import com.example.hw27.Api.ApiResponse;
import com.example.hw27.Model.Blog;
import com.example.hw27.Model.User;
import com.example.hw27.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;




    @GetMapping("/get-all")
    public ResponseEntity getAllBlog() {
    return ResponseEntity.status(200).body(blogService.getAllBlog());
}
    @GetMapping("/get")
    public ResponseEntity getMyBlog(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getMyBlog(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog) {
        blogService.addBlog(blog,user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Add blog"));
    }

    @PutMapping("/update/{blog_id}")
    public ResponseEntity updateBlog(@RequestBody @Valid Blog blog,@AuthenticationPrincipal User user ,@PathVariable Integer blog_id) {
        blogService.updateBlog(blog,user.getId(),blog_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Update blog"));
    }

    @DeleteMapping("/delete/{blog_id}")
    public ResponseEntity deleteBlog( @PathVariable Integer blog_id,@AuthenticationPrincipal User user) {
        blogService.deleteBlog(blog_id,user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Delete blog"));
    }

     @GetMapping("/get-title/{title}")

    public ResponseEntity gettitle(@AuthenticationPrincipal User user,@PathVariable String title){
         return ResponseEntity.status(HttpStatus.OK).body(blogService.getTitle(title,user.getId()));

     }
    @GetMapping("/get-id/{blog_id}")
    public ResponseEntity getId(@AuthenticationPrincipal User user,@PathVariable Integer blog_id){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getId(blog_id,user.getId()));

    }
   /* @GetMapping("/get-title2/{title}")

    public ResponseEntity getalltitle(@AuthenticationPrincipal User user,@PathVariable String title){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAlltitle(title,user.getId()));

    }*/

}
