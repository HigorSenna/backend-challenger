package br.com.challenger.backendchallenger.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:bdd/features", monochrome = true)
public class StoreTest {
}
