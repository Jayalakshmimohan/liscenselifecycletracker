package com.training.liscenselifecycletracker.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.training.liscenselifecycletracker.service.TechnicalSupportService;

public class TechnicalSupportControllerTest {

	private MockMvc mockMvc;

	@Mock
	private TechnicalSupportService technicalSupportService;

	@InjectMocks
	private TechnicalSupportController technicalSupportController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(technicalSupportController).build();
	}

	@Test
	public void testLogFault() throws Exception {
		mockMvc.perform(post("/api/technicalsupport/support/faults").param("deviceId", "1")
				.param("description", "Fault description").param("date", "2023-01-01").param("category", "Hardware"))
				.andExpect(status().isOk());

		verify(technicalSupportService, times(1)).logFault(1L, "Fault description", "2023-01-01", "Hardware");
	}

	@Test
	public void testUpdateFaultLog() throws Exception {
		mockMvc.perform(put("/api/technicalsupport/support/faults/deviceId").param("deviceId", "1")
				.param("repairDetails", "Repaired").param("category", "Hardware").param("eventType", "Repair"))
				.andExpect(status().isOk());

		verify(technicalSupportService, times(1)).updateFaultLog(1L, "Repaired", "Hardware", "Repair");
	}

	@Test
	public void testViewEndOfSupportDates() throws Exception {
		List<String> mockDates = Arrays.asList("2024-12-31", "2025-01-01");
		when(technicalSupportService.viewEndOfSupportDates()).thenReturn(mockDates);

		mockMvc.perform(get("/api/technicalsupport/support/dates")).andExpect(status().isOk())
				.andExpect(content().json("[\"2024-12-31\", \"2025-01-01\"]"));

		verify(technicalSupportService, times(1)).viewEndOfSupportDates();
	}
}
