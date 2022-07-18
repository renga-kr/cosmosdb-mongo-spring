package com.renga.mongodb;

import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
@Slf4j
public class MongodbApplication implements CommandLineRunner {
	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		locationRepository.deleteAll();
		LocalDate startDate = LocalDate.of(2022, 10, 1);
		LocalDate endDate = LocalDate.of(2022, 10, 10);


		LocalDate startDate1 = LocalDate.of(2022, 10, 11);
		LocalDate endDate1 = LocalDate.of(2022, 10, 20);

		Location main = new Location("Main", "1", "1", startDate, endDate);
		locationRepository.save(main);
		Location main1 = new Location("Main1", "1", "2", startDate, endDate);
		locationRepository.save(main1);
		Location main2 = new Location("Main2", "1", "3", startDate1, endDate1);
		locationRepository.save(main2);
		Location main3 = new Location("Main3", "1", "4", startDate1, endDate1);
		locationRepository.save(main3);
		Location main4 = new Location("Main4", "1", "5", startDate1, endDate1);
		locationRepository.save(main4);
		LocalDate queryDate = LocalDate.of(2022, 10, 12);
		List<Location> locations = locationRepository.findAll();
		List<Location> bySomeRange = locationRepository.findBySomeRange(queryDate);

		Query query = new Query();
		query.addCriteria(
				Criteria.where("startDate").lte(queryDate)
						.andOperator(
								Criteria.where("endDate").gt(queryDate)
						)
		);

		List<Location> locations1 = mongoTemplate.find(query, Location.class);
		log.info(locations.toString());
	}
}
