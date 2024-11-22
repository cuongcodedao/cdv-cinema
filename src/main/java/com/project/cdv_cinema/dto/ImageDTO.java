package com.project.cdv_cinema.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDTO {
    private String urlImage;
    private String type;
    private boolean isPoster;
}
