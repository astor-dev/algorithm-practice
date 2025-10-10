import java.util.*;

class Solution {
    /**
     표의 좌표가 값을 지닐 때, 참조형 변수를 지님(instance)
     참조형 변수 <-> 좌표 값을 양방향 참조
     MERGE/UNMERGE 시 이를 통해 접근
     */
    static final String EMPTY = "EMPTY";

    static class XY {
        int x;
        int y;
        XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class CellValue {
        String value;
        List<XY> refXys;
        CellValue(String value) {
            this.value = value;
            this.refXys = new ArrayList<>();
        }
    }

    // 1-based
    static CellValue[][] chart = new CellValue[51][51];
    static Map<String, List<CellValue>> valueMap = new HashMap<>();
    static List<String> printResult = new ArrayList<>();
    public String[] solution(String[] commands) {
        StringTokenizer st;
        for(String command: commands) {
            st = new StringTokenizer(command);
            String operation = st.nextToken();
            switch(operation) {

                case "UPDATE":
                    String v1 = st.nextToken();
                    String v2 = st.nextToken();
                    if(st.hasMoreTokens()) {
                        // UPDATE r c value
                        update(Integer.parseInt(v1), Integer.parseInt(v2), st.nextToken());
                    } else {
                        // UPDATE value1 value2
                        update(v1, v2);
                    }
                    break;
                case "MERGE":
                    merge(
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    );
                    break;
                case "UNMERGE":
                    unmerge(
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    );
                    break;
                case "PRINT":
                    print(
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    );
                    break;
            }
        }

        String[] answer = printResult.toArray(new String[0]);
        return answer;
    }

    static void update(int r, int c, String value) {
        XY xy = new XY(r, c);
        CellValue cell = chart[xy.x][xy.y];
        if(cell != null) {
            valueMap.get(cell.value).remove(cell);
            cell.value = value;
            valueMap.computeIfAbsent(cell.value, k -> new ArrayList<>()).add(cell);
        } else {
            cell = new CellValue(value);
            valueMap.computeIfAbsent(value, k -> new ArrayList<>()).add(cell);
            chart[xy.x][xy.y] = cell;
            cell.refXys.add(xy);
        }
    }

    static void update(String value1, String value2) {
        List<CellValue> cellsToMove = valueMap.remove(value1);
        if(cellsToMove != null) {
            for(CellValue cell : cellsToMove) {
                cell.value = value2;
            }
            valueMap.computeIfAbsent(value2, k -> new ArrayList<>()).addAll(cellsToMove);
        }
    }

    static void unmerge(int r, int c) {
        CellValue cellValue = chart[r][c];
        if(cellValue == null) {
            return;
        }
        for(XY xy: cellValue.refXys) {
            chart[xy.x][xy.y] = null;
        }
        chart[r][c] = cellValue;
        cellValue.refXys = new ArrayList<>();
        cellValue.refXys.add(new XY(r,c));
    }

    static void merge(int r1, int c1, int r2, int c2) {
        CellValue cellValue1 = chart[r1][c1];
        CellValue cellValue2 = chart[r2][c2];
        if(cellValue1 == null && cellValue2 == null) {
            CellValue emptyCell = new CellValue(EMPTY);
            chart[r1][c1] = emptyCell;
            chart[r2][c2] = emptyCell;
            emptyCell.refXys.add(new XY(r1,c1));
            emptyCell.refXys.add(new XY(r2,c2));
            valueMap.computeIfAbsent(emptyCell.value, k -> new ArrayList<>()).add(emptyCell);
            return;
        }
        if(cellValue1 == cellValue2) {
            return;
        }
        if(cellValue1 == null) {
            chart[r1][c1] = cellValue2;
            cellValue2.refXys.add(new XY(r1,c1));
        } else if(cellValue2 == null) {
            chart[r2][c2] = cellValue1;
            cellValue1.refXys.add(new XY(r2,c2));
        } else {
            // NOTE: 만약 1이 빈 값인 경우 우선순위를 바꿈
            if(cellValue1.value.equals(EMPTY)) {
                valueMap.get(cellValue1.value).remove(cellValue1);
                chart[r1][c1] = cellValue2;
                for(XY xy: cellValue1.refXys) {
                    chart[xy.x][xy.y] = cellValue2;
                    cellValue2.refXys.add(xy);
                }
            } else {
                valueMap.get(cellValue2.value).remove(cellValue2);
                chart[r2][c2] = cellValue1;
                for(XY xy: cellValue2.refXys) {
                    chart[xy.x][xy.y] = cellValue1;
                    cellValue1.refXys.add(xy);
                }
            }

        }
    }

    static void print(int r, int c) {
        CellValue valueOrNull = chart[r][c];
        if(valueOrNull == null) {
            printResult.add(EMPTY);
        } else {
            printResult.add(valueOrNull.value);
        }
    }
}