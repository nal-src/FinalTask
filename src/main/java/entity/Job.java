package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Job {
    private int id;
    private String title;
    private String description;
    private int price;
    private String user;
    private int noOfComments;
}
