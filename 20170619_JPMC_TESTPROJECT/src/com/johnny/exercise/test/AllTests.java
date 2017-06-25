package com.johnny.exercise.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AdjustmentReportTest.class, MessageTest.class,
		SaleReportTest.class, SaleTest.class })
public class AllTests {

}
