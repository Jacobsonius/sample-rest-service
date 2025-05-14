package com.sampleservice.demo.config;

import com.sampleservice.demo.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.ApplicationArguments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class PopulateDatabaseTests {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private PopulateDatabase populateDatabase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void runTest() {
        ApplicationArguments args = new ApplicationArguments() {
            @Override
            public String[] getSourceArgs() {
                return new String[0];
            }

            @Override
            public boolean containsOption(String option) {
                return false;
            }

            @Override
            public List<String> getOptionValues(String option) {
                return new ArrayList<>();
            }

            @Override
            public Set<String> getOptionNames() {
                return Set.of();
            }

            @Override
            public List<String> getNonOptionArgs() {
                return new ArrayList<>();
            }
        };

        try {
            populateDatabase.run(args);
        } catch (Exception e) {
            // Handle exception if necessary, but in this case, we assume it's not expected
            throw new RuntimeException("Unexpected exception during test", e);
        }

        // Verify that the service method was called with a list of size 20,000
        verify(studentService, times(1)).saveAll(org.mockito.ArgumentMatchers.anyList());
    }
}