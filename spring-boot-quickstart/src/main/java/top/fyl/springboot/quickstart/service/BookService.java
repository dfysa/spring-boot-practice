package top.fyl.springboot.quickstart.service;

import org.springframework.stereotype.Service;
import top.fyl.springboot.quickstart.dto.BookDTO;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/9/2 下午4:01
 * @description
 */
@Service
public class BookService {
    public List<BookDTO> getAllBooks(){
        return List.of(
                new BookDTO(1l,"java Programming","Alice",29.99),
                new BookDTO(2l,"Spring Boot in Action","Bob",34.99)
        );
    }
}
