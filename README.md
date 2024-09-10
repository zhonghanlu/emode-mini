# Emode开发文档

```angular2html
代码提交到 review 分支
```

## 1.项目结构

### 1.1 app模块

```angular2html
    此模块为统一接口暴露层，以及启动层，分为controller与runner
controller为接口暴露层，细分为多个小模块
runner为启动层，为启动添加额外内容
```

### 1.2 biz模块

```angular2html
    此模块为顶层业务层，细分多个小模块，通过聚合基础业务层，实现详细功能
达到事务统一管理
```

### 1.3 common模块

```angular2html
    此模块为通用工具层，以及常量池层。提供基础工具，共享类，常量等
```

### 1.4 core模块

```angular2html
    此模块为框架核心层，包含切面的定义，框架特色功能的产出，权限的统一设计，
web层框架的定制化
```

### 1.5 auth模块

```angular2html
    此模块为权限控制层，提供权限控制，权限校验，权限配置，权限缓存等
```

### 1.6 base模块

```angular2html
    此模块为基础业务层，提供基础业务等
```

### 1.7 file模块

```angular2html
    此模块为基础文件存储层，目前仅有MiniO接入，后期可接入其他存储
```

## 2.开发规范

### 2.1 接口规范：

```angular2html
不宜过长，见名知意，多个单词 ‘-’ 隔开，编写openApi3的对应文档
```
### 2.2 代码规范：

```angular2html
不强制要求解耦，但尽量减少耦合，尽量减少重复代码；结构化编写代码
```

### 2.3 实体转换：

```angular2html
尽量使用mapstruct
```

### 2.4 异步代码规范：

```angular2html
控制事务粒度，尽量减少事务嵌套，可以选择手动进行异步处理
```

### 2.5 缓存规范：

```angular2html
控制缓存一致，避免大key
```

## 写在最后：
```angular2html
    这个框架我采用了我自己制定的开发规范编写的，参考了很多开源框架，进行选择，筛选，重构。
最终零零散散耗时三个月把这个项目推出第一版本

    欢迎各位提出更好的代码，一起维护这个emode，做一个知识共享的emode
    更好建议，欢迎提issue
```