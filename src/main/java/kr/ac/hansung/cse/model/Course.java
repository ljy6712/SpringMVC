package kr.ac.hansung.cse.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {
    private int id;
    //year, semester, type, subject, credit, professor, code
    private int year;

    private int semester;

    private String subject;

    private String professor;

    private String type;

    private int credit;

    private String code;
}