package io.fredwang.qop;

import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.SqlNode;
import java.sql.*;

public class HelloPlan {
  public static void main(String[] args) throws Exception {
    SqlNode ast = SqlParser.create("SELECT 1").parseStmt();
    System.out.println("Calcite AST:\n" + ast);

    try (Connection conn = DriverManager.getConnection("jdbc:duckdb:")) {
      try (Statement st = conn.createStatement()) {
        ResultSet rs = st.executeQuery("SELECT '👋' as msg");
        if (rs.next()) System.out.println("DuckDB says: " + rs.getString(1));
      }
    }
  }
}
