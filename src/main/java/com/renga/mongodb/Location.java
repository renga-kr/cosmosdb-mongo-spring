package com.renga.mongodb;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Location {
  @Id
  private String id;
  private String name;
  private String rank;
  private String priority;
  private LocalDate startDate;
  private LocalDate endDate;

  public Location(String name, String rank, String priority, LocalDate startDate, LocalDate endDate) {
    this.name = name;
    this.rank = rank;
    this.priority = priority;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
