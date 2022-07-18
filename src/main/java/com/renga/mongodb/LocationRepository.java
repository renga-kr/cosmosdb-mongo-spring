package com.renga.mongodb;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
  @Query("{ startDate : { $lte : { $date : ?0}}, $and : [{ endDate : { $gt : { $date : ?0}}}]}")
  List<Location> findBySomeRange(LocalDate queryDate);
}
