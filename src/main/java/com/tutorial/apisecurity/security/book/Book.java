package com.tutorial.apisecurity.security.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
   private String bookId;
   private String isbn;
   private String title;
   private String publisher;
   private String datePublished;
}
