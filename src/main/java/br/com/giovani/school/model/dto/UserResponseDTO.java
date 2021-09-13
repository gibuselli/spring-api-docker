package br.com.giovani.school.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private String email;

}
