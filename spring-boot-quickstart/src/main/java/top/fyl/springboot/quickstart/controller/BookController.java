package top.fyl.springboot.quickstart.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.quickstart.dto.BookDTO;
import top.fyl.springboot.quickstart.service.BookService;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/9/2 下午4:03
 * @description
 */
@RestController
@RequestMapping("/books")
public class BookController {
    @Resource
    private BookService bookService;
    @GetMapping()
    public List<BookDTO> getBooks(){
        return bookService.getAllBooks();
    }
}
