package flotte.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Image {
    @Id
    private Long id;
    private String data;
    private String format;
}
