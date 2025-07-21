

package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UpdateDto {
    private Long id;
    private String name;
    private String email;
}