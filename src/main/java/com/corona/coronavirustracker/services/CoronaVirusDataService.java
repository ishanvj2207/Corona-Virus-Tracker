package com.corona.coronavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.corona.coronavirustracker.models.LocationStat;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CoronaVirusDataService {
    private static String URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	
    private List<LocationStat> allStats = new ArrayList<>();
    
	public List<LocationStat> getAllStats() {
		return allStats;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
	    List<LocationStat> newStats = new ArrayList<>();
		HttpClient client =  HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();
		HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString() );

		StringReader csvReader = new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		for (CSVRecord record : records) {
			LocationStat stat = new LocationStat();
		    String state = record.get("Province/State");
		    stat.setState(state);
		    String country = record.get("Country/Region");
		    stat.setCountry(country);
		    int totalCases = Integer.parseInt(record.get(record.size() - 1));
		    stat.setTotalCases(totalCases);
		    int PreviousDayCases = Integer.parseInt(record.get(record.size() - 2));
		    stat.setChangesSinceLastDay(totalCases-PreviousDayCases);
		    newStats.add(stat);
//		    System.out.println(stat);
		}
		this.allStats = newStats;
	}
}
