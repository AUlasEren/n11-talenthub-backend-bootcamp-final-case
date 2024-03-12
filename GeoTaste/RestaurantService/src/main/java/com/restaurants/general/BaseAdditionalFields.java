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
    @Indexed(name = "creator_id", type = "update")
    private String creatorId;
    @Indexed(name = "updater_id", type = "update")
    private String updaterId;

}
