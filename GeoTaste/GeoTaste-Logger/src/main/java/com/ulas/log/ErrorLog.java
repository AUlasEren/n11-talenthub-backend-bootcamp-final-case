package com.ulas.log;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class ErrorLog {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ErrorLog_SEQ")
  @SequenceGenerator(name = "ErrorLog_SEQ", sequenceName = "ERROR_LOG_ID_SEQ", allocationSize = 1)
  private Long id;

  private LocalDateTime date;
  private String message;
  private String description;
}
