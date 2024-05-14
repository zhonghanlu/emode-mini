package com.mini.common.utils.webmvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * @author zhl
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PageBuilder<T> {

    @Schema(name = "当前页" ,type = "long")
    private long current;

    @Schema(name = "页大小",type = "long")
    private long size;

    @Schema(name = "总数",type = "long")
    private long total;

    @Schema(name = "数据详情",type = "long")
    private List<T> records;

    public static <T> PageBuilder<T> pB(IPage<T> page, List<T> records) {
        return PageBuilder.<T>builder()
                .current(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .records(records).build();
    }
}
