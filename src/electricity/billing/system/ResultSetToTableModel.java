package electricity.billing.system;

import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ResultSetToTableModel {

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // Column names
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Rows
        Vector<Vector<Object>> data = new Vector<>();

        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int col = 1; col <= columnCount; col++) {
                row.add(rs.getObject(col));
            }
            data.add(row);
        }

        return new DefaultTableModel(data, columnNames);
    }
}

