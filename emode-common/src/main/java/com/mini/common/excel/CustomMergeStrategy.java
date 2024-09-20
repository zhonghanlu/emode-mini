package com.mini.common.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.mini.common.annotation.CollectCustomMerge;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhl
 * @Description: 自定义单元格合并策略类CustomMergeStrategy，当Excel中两列主键相同时，合并被标记需要合并的列
 */
public class CustomMergeStrategy implements RowWriteHandler {
    /**
     * 主键下标
     */
    private Integer pkIndex;

    /**
     * 需要合并的列的下标集合
     */
    private List<Integer> needMergeColumnIndex = new ArrayList<>();

    /**
     * DTO数据类型
     */
    private Class<?> elementType;

    /**
     * 字段名称到列索引的映射
     */
    private Map<String, Integer> fieldToIndexMap = new HashMap<>();

    /**
     * 缓存合并
     */
    private Map<Integer, List<CellRangeAddress>> mergedRegions = new HashMap<>();

    public CustomMergeStrategy(Class<?> elementType) {
        this.elementType = elementType;
        initFieldToIndexMap();
    }

    @Override
    public void beforeRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Integer rowIndex, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer relativeRowIndex, Boolean isHead) {
        if (Boolean.TRUE.equals(isHead) || row.getRowNum() <= 1) {
            return;
        }

        Sheet sheet = writeSheetHolder.getSheet();
        Row lastRow = sheet.getRow(row.getRowNum() - 1);

        if (lastRow == null || lastRow.getCell(pkIndex) == null || row.getCell(pkIndex) == null) {
            return;
        }

        String lastValue = lastRow.getCell(pkIndex).getStringCellValue();
        String currentValue = row.getCell(pkIndex).getStringCellValue();

        if (lastValue.equalsIgnoreCase(currentValue)) {
            List<CellRangeAddress> currentMerges = mergedRegions.computeIfAbsent(row.getRowNum() - 1, k -> new ArrayList<>());

            for (Integer columnIndex : needMergeColumnIndex) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(row.getRowNum() - 1, row.getRowNum(), columnIndex, columnIndex);
                currentMerges.add(cellRangeAddress);
            }
        } else {
            List<CellRangeAddress> currentMerges = mergedRegions.remove(row.getRowNum() - 1);
            if (currentMerges != null) {
                for (CellRangeAddress cellRangeAddress : currentMerges) {
                    sheet.addMergedRegionUnsafe(cellRangeAddress);
                }
            }

            // 批量处理合并操作
            applyMergedRegions(sheet);
        }
    }


    /**
     * 批量处理合并操作
     */
    private void applyMergedRegions(Sheet sheet) {
        List<CellRangeAddress> allMerges = new ArrayList<>();
        mergedRegions.values().forEach(allMerges::addAll);
        mergedRegions.clear(); // 清空缓存

        for (CellRangeAddress cellRangeAddress : allMerges) {
            sheet.addMergedRegionUnsafe(cellRangeAddress);
        }
    }

    /**
     * 初始化主键下标和需要合并字段的下标
     */
    private void initFieldToIndexMap() {
        // 获取DTO所有的属性
        Field[] fields = elementType.getDeclaredFields();
        int index = 0;

        // 遍历所有的字段，因为是基于DTO的字段来构建excel，所以字段数 >= excel的列数
        for (Field theField : fields) {
            // 获取@ExcelProperty注解，用于获取该字段对应在excel中的列的下标
            ExcelProperty easyExcelAnno = theField.getAnnotation(ExcelProperty.class);
            // 为空,则表示该字段不需要导入到excel,直接处理下一个字段
            if (null == easyExcelAnno) {
                continue;
            }

            // 获取自定义的注解，用于合并单元格
            CollectCustomMerge collectCustomMerge = theField.getAnnotation(CollectCustomMerge.class);

            // 没有@CustomMerge注解的默认不合并
            if (null == collectCustomMerge) {
                continue;
            }

            // 将字段和excel的表头匹配上
            fieldToIndexMap.put(easyExcelAnno.value()[0], index);

            if (collectCustomMerge.isPk()) {
                pkIndex = index;
            }

            if (collectCustomMerge.needMerge()) {
                needMergeColumnIndex.add(index);
            }

            index++;
        }

        // 没有指定主键，则异常
        if (null == this.pkIndex) {
            throw new IllegalStateException("使用@CustomMerge注解必须指定主键");
        }
    }
}
