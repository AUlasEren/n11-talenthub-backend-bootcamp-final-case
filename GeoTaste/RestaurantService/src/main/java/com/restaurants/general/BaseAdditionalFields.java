package com.restaurants.general;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.solr.core.mapping.Indexed;
import java.util.Date;


@Getter
@Setter
public class BaseAdditionalFields {
    @Indexed(name = "create_date", type = "update")
    private Date createDate;

    @Indexed(name = "update_date", type = "update")
    private Date updateDate;
    private String creatorId;
    private String updaterId;

}
