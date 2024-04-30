package com.mini.common.utils.webmvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PageBuilder<T> {

    @ApiModelProperty(value = "当前页")
    private long current;

    @ApiModelProperty(value = "页大小")
    private long size;

    @ApiModelProperty(value = "总数")
    private long total;

    @ApiModelProperty(value = "数据详情")
    private List<T> records;

    public static <T> PageBuilder<T> pB(IPage<T> page, List<T> records) {
        return PageBuilder.<T>builder()
                .current(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .records(records).build();
    }
}
