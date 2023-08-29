package com.example.hw27.Repository;

import com.example.hw27.Model.Blog;
import com.example.hw27.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
 List<Blog> findAllByUser (User user);

 Blog findAllById(Integer id);
 Blog findAllByTitle (String title);
 List<Blog> findBlogByTitle(String title);
  List<Blog> findBlogById(Integer id);
}
