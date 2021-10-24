package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Comment {
    private int id;
    private String message;
    private String username;
    private String commentDate;
}