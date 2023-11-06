package com.amolli.oyeongshop.ver2.board.s3;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@Builder
public class Photo {
    private String originalName;
    private String uniqueName;
    private String type;
    @Id
    private String url;
    private String purpose;
}
