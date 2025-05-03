package io.fredwang.qop;

import org.apache.calcite.plan.RelOptUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PlannerSmokeTest {

  @Test
  void parsesSelectLiteral() throws Exception {
    String plan = RelOptUtil.toString(
        MyPlanner.logicalPlan("SELECT 42"));
    assertTrue(plan.contains("LogicalValues"),
        () -> "Plan was:\n" + plan);
  }
}
