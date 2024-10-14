package top.fyl.springboot.database.entity;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dfysa
 * @data 8/10/2024 下午1:54
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="t_article")

public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 32)
    private String author;

    @Column(nullable =false,unique=true,length=32)
    private String title;

    @Column (length = 32)
    private String content;



}
