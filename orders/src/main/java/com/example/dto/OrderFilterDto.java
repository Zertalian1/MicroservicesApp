package com.example.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilterDto {
    private LocalDateTime orderDateFrom;
    private LocalDateTime orderDateTo;
    private LocalDateTime completedDateFrom;
    private LocalDateTime completedDateTo;
    private String customerFirstName;
    private String customerLastName;
}
