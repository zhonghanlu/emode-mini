<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">MySQL规范文档</h1>

### 库
1. 库名使用小写字母，下划线分隔
2. 统一使用 utf8mb4 编码，utf8mb4_general_ci 编码
3. 使用mysql:8.x.x
### 表
1. 表名使用小写字母，下划线分隔
2. 见名知意，相同模块相同前缀
### 字段
1. 字段名使用小写字母，下划线分隔
2. 主键统一使用id，外键使用xxx_id，统一使用bigint
3. 必须有create_time、update_time、create_by、update_by、del_flag字段；del_flag 默认值为未删除
4. 时间类型使用datetime、id类型使用bigint禁止使用int、del_flag类型使用tinyint
5. 库内枚举值注释需和项目同步更新，枚举值使用项目搭配的handler（详情见common.mybatis）
### 关联
1. 注意表之间的关联的笛卡尔积问题
2. 测一遍count的效率，再考虑使用mybatis-plus分页

有更好的建议请提issue