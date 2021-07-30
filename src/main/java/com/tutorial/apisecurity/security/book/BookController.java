package com.tutorial.apisecurity.security.book;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

   @GetMapping("/{bookId}")
   public Book findById(@PathVariable String bookId) {
      Book book = new Book(bookId, UUID.randomUUID().toString(), "API Security", "UDemy", "07-29-2021");
      return book;
   }
}
