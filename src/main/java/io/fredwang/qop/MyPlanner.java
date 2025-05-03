package io.fredwang.qop;

import org.apache.calcite.jdbc.JavaTypeFactoryImpl;
import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.RelRoot;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.tools.*;

public final class MyPlanner {
  private static final FrameworkConfig CONFIG =
      Frameworks.newConfigBuilder()
                .defaultSchema(Frameworks.createRootSchema(true))
                .build();

  public static RelNode logicalPlan(String sql) throws Exception {
    Planner planner = Frameworks.getPlanner(CONFIG);
    SqlNode ast   = planner.parse(sql);
    SqlNode valid = planner.validate(ast);
    RelRoot root  = planner.rel(valid);
    return root.rel;
  }

  public static void main(String[] args) throws Exception {
    RelNode rel = logicalPlan("SELECT 1+2 AS three");
    System.out.println(RelOptUtil.toString(rel));
    System.out.println(
      org.apache.calcite.plan.RelOptUtil.toString(
          MyPlanner.logicalPlan("SELECT 42")));
  }
}
