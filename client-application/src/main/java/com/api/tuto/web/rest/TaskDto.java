package com.api.tuto.web.rest;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * A TaskDto.
 */
@Data
@NoArgsConstructor
public class TaskDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String content;

    private Boolean completed;

    private Date createdAt;

}
