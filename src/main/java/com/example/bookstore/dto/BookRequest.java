package com.example.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Payload for creating or updating a book")

public record BookRequest(
        @Schema(example = "Clean Code")
        @NotBlank(message = "title is required")
        String title,

        @Schema(example = "Robert C. Martin")
        @NotBlank(message = "author is required")
        String author,

        @Schema(example = "9780132350884")
        String isbn,

        @Schema(example = "2008")
        @Min(1000) @Max(2100)
        Integer publishedYear
) {
}
