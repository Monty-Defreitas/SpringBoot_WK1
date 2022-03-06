package com.promineotech.jeep.Entity;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * This class is used to parse String data from the JSON input. Into an object. The Fields correspond with the database identifiers needed for the querying of information.
 */
@Data
@Builder
public class OrderRequest {
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
  private String customer;

  @NotNull
  private JeepModel model;
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
  private String trim;
  @Positive
  @Min(2)
  @Max(4)
  private int doors;
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
  private String color;
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
  private String engine;

  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
  private String tire;

  private List<@NotNull @Length(max = 30) @Pattern(regexp = "[A-Z0-9_]*") String> options;
}
