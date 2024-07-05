### 2024年4月30日15:41:16

#### 初始化项目

- 项目结构搭建
- 新增app模块（负责web层交互）
- 新增common模块（负责公共模块）
- 新增biz模块（负责最顶层业务）
- 新增core模块（负责项目核心功能）
- 新增auth模块（负责权限模块）

### 2024年5月8日09:41:31

#### 优化项目结构

- 修改项目依赖结构（web层依赖core层）
- 修改内部容器Undertow
- 初始化日志
- 新增全局异常处理（待测试）
- 新增全局bean处理（待测试）
- 新增knife模块 OpenAPI3 语法 不依赖fox （待深度使用）

### 2024年5月10日14:12:57

#### 集成缓存模块

- 集成redisson分布式缓存
- 集成lock4j分布式锁
- 添加jackson工具类
- 部分依赖版本升级(下次做升级)

### 2024年5月11日09:37:25

#### 升级依赖版本

- 升级springboot mysql druid版本
- 选择权限框架：sa-token
- 基础整合sa-token框架
- 修改数据源使用HikariDataSource
- 修改全局异常处理
- 登录基础功能开发（下次做更新）
- mvn release 发布

### 2024年5月14日10:06:51

#### 更新mvn release 发布

- 仓库采用阿里云效
- 不对地址做ssl校验
- git config --global http.sslVerify false
- 本地所有文件提交
- mvn release:clean
- mvn release:prepare
- mvn release:perform
- 出现问题及时回滚
- mvn release:rollback
- tag冲突及时删除git tag -d tagName
- MySQL规范文档输出
- 修复所有issue

### 2024年5月14日17:53:39

- 用户表CRUD初始化
- 项目开发结构优化，日志优化
- 项目开发层级模式优化
- 用户CRUD操作完善，确定开发模式（下次更新）

### 2024年5月15日09:33:57

- mapperStruct出现问题（下次处理）

### 2024年5月15日14:40:21

- 添加knife4j静态过滤文件
- 优化mvc前置拦截器
- 优化knife4j配置，分组待处理（TODO）
- 文件系统添加（下次更新）
- mapperStruct依旧出现问题（下次处理）

### 2024年5月16日08:27:07

- 处理mapperStruct问题
- mapperStruct如果反复重启仍然是空对象，
- 使用mvn编译运行下，或着将.idea文件删除，刷新，重启即可
- 注意target文件及时删除测试

### 2024年5月16日16:02:21

- 添加文件系统模块
- 添加minio整合

### 2024年5月17日16:51:221

- 处理分页（下次更新）

### 2024年5月21日11:02:55

- 添加分页接口，处理分页
- common包下utils.webmvc.PageQuery 类
- common包下utils.webmvc.PageBuilder 类
- 转换使用mapperStruct
- 注意OpenApi3的语法

### 2024年5月21日14:52:02

- 此项目雏形已成，暂且告一段落

  TODO：待更新：系统权限

=================================================================

### 2024年5月23日11:01:11

- 添加httpclient工具类
- 添加集合切分工具类

### 2024年5月28日17:17:31

- 添加日志搜索 requestId
- 优化全局aopConfig
- 修改数据源为阿里云rds

### 2024年5月30日18:01:40

- 加入鸽鸽banner

### 2024年6月3日17:15:03

- 添加RBAC权限模型
- 添加角色关联分页查询
- PC登录功能实现(待处理)

### 2024年6月4日17:23:53

- 修改同意结果集返回
- 角色和权限关联关系CRUD
- 角色和权限关联关系(待测试)
- 用户和角色关联关系CRUD(待编写)

### 忙于刷算法，暂未更新 2024年6月12日13:45:56

### 暂缓更新，最近有点忙 2024年6月20日15:40:54

### 七月份开始更新权限模块 2024 年  7 月 2 日 23:19:38

### 2024年7月4日15:14:37

- 优化权限设计----》 修改为权限表为 子父级别菜单 + 权限
- 待新增地区表、部门表（校区表）、岗位表 【TODO】
- 主管理端可以访问全部权限-部门可访问部门权限 【TODO】
- 优化项目整理文档
- 权限CRUD编写【待测试】
- 权限与用户模块整体测试【待测试】




