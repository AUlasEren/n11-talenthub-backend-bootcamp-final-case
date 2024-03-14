package com.ulas.log;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class ErrorLog {

  @Id
  @GeneratedValue(generator = "ErrorLog", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "ErrorLog", sequenceName = "ERROR_LOG_ID_SEQ")
  private Long id;

  private LocalDateTime date;
  @Column(name = "MESSAGE", length = 1000)
  private String message;
  @Column(name = "DESCRIPTION", length = 1000)
  private String description;
}
