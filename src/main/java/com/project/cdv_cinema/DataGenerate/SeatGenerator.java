package com.project.cdv_cinema.DataGenerate;

public class SeatGenerator {
    public static void main(String[] args) {
        StringBuilder sql = new StringBuilder();
        String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}; // 10 hàng
        int totalCols = 10; // Tổng số cột (1-10)
        int roomId = 1; // ID của phòng

        for (int row = 1; row <= totalCols; row++) {
            int idCol = 1;
            for (int col = 1; col <= totalCols; col++) {
                if (col == 2 || col == 3) {
                    continue;
                }
                String code = String.valueOf((char)(row + 64)) + idCol;
                String status = "AVAILABLE"; // Trạng thái ghế
                String type = "REGULAR"; // Loại ghế

                // Thêm câu lệnh INSERT vào chuỗi
                sql.append("INSERT INTO cdvcinema.seat (code, col_id, row_id, status, type, room_id) ")
                        .append("VALUES ('").append(code).append("', '").append(col).append("', '")
                        .append(row).append("', '").append(status).append("', '").append(type).append("', '")
                        .append(roomId).append("');\n");
                idCol++;
            }
        }

        // In ra câu lệnh SQL
        System.out.println(sql.toString());
    }
}
