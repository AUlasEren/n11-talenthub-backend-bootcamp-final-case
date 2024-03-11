package com.ulas.general;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long creatorId;
    private Long updaterId;
}
