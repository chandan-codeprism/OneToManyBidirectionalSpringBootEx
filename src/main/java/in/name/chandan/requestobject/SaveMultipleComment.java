package in.name.chandan.requestobject;

import lombok.Data;

import java.util.List;

@Data
public class SaveMultipleComment {

    private List<String> comments;
    private Integer userId;
}
