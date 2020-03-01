package com.corona.coronavirustracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.corona.coronavirustracker.models.LocationStat;
import com.corona.coronavirustracker.services.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService service;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStat> allStats = service.getAllStats();
		int totalCasesReported = allStats.stream().mapToInt(stat -> stat.getTotalCases()).sum();
		int newCasesReported = allStats.stream().mapToInt(stat -> stat.getChangesSinceLastDay()).sum();
		model.addAttribute("LocationStats", allStats);
		model.addAttribute("totalCasesReported", totalCasesReported);
		model.addAttribute("newCasesReported", newCasesReported);
		
		return "home";
	}
}
