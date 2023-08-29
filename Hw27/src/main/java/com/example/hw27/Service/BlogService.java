package com.example.hw27.Service;

import com.example.hw27.Api.ApiException;
import com.example.hw27.Model.Blog;
import com.example.hw27.Model.User;
import com.example.hw27.Repository.AuthRepository;
import com.example.hw27.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    public List<Blog> getMyBlog(Integer user_id) {
        User user = authRepository.findUserById(user_id);
        return blogRepository.findAllByUser(user);
    }

    public void addBlog(Blog blog, Integer user_id) {
        User user = authRepository.findUserById(user_id);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Blog blog, Integer user_id, Integer blog_id) {
        User user = authRepository.findUserById(user_id);
        Blog blog1 = blogRepository.findAllById(blog_id);
        if (blog1 == null) {
            throw new ApiException("the blog not found");
        }
        if (blog1.getUser().getId() != user_id) {
            throw new ApiException("the blog not found");
        }
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blogRepository.save(blog1);
    }

    public void deleteBlog(Integer user_id, Integer blog_id) {
        Blog blog = blogRepository.findAllById(blog_id);
        if (blog == null) {
            throw new ApiException("the blog not found");
        }
        if (blog.getUser().getId() != user_id) {
            throw new ApiException("the blog not found");

        }
        blogRepository.delete(blog);
    }


    public Blog getTitle(String title ,Integer user_id){
        Blog blog=blogRepository.findAllByTitle(title);
        User user=authRepository.findUserById(user_id);
        if (blog == null) {
            throw new ApiException(" title not found");
        }
        if (user==null){
            throw new ApiException(" user not found");
        }
        if (blog.getUser().getId() != user_id) {
            throw new ApiException("User and blog id not equal");

        }
        return blog;
    }

    /*public List<Blog> getAlltitle(String title ,Integer user_id){
        List<Blog> blogs=blogRepository.findBlogByTitle(title);
        User user=authRepository.findUserById(user_id);
        if (blogs == null) {
            throw new ApiException(" title not found");
        }
        if (user==null){
            throw new ApiException(" user not found");
        }
        if (blogs.equals(user_id)) {
            throw new ApiException("User and blog id not equal");

        }
        return blogs;
    }*/




    public Blog getId(Integer blog_id,Integer user_id){
       Blog blog=blogRepository.findAllById(blog_id);
        User user=authRepository.findUserById(user_id);
        if (blog == null) {
            throw new ApiException(" blog not found");
        }
       if (user==null){
           throw new ApiException(" user not found");
       }
        if (blog.getUser().getId() != user_id) {
            throw new ApiException("User and blog id not equal");

        }
        return blog;
    }

}
