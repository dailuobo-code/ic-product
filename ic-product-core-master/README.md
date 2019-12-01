# IC Product Core

## 项目结构

### 项目目录结构

```txt
.
├── ic-category               # 类目中心
│   ├── ic-category-api       + 类目、属性的模型、枚举、接口等定义
│   ├── ic-category-core      + 类目中心业务实现
│   └── ic-category-starter   + 类目启动模块
│
├── ic-item                   # 商品中心
│   ├── ic-item-api           + 商品、城市商品等模型、枚举接口定义
│   ├── ic-item-core          + 商品中心业务实现
│   └── ic-item-starter       + 商品启动模块
│
├── ic-base                   # 商品基础工具类
├── ic-test                   # 商品中心集成测试
└── ic-bootstrap-starter      # 启动模块
```

### 类目模块目录结构

### 商品模块目录结构

```txt
.
└── ic-item
    ├── ic-item-api
    │   └── com.mallcai.ic
    │       ├── item
    │       │   ├── api
    │       │   │   ├── bean
    │       │   │   │   ├── request
    │       │   │   │   └── response
    │       │   │   └── facade
    │       │   └── enums
    │       ├── sku
    │       │   └── enums
    │       └── spu
    │           ├── api
    │           │   ├── bean
    │           │   │   ├── request
    │           │   │   └── response
    │           │   └── facade
    │           └── enums
    └── ic-item-core
        └── com.mallcai.ic
            ├── base
            │   ├── aop
            │   ├── model
            │   └── utils
            ├── item
            │   ├── api
            │   │   ├── converter
            │   │   │   ├── input
            │   │   │   └── output
            │   │   └── facade
            │   ├── manager
            │   ├── model
            │   ├── repository
            │   └── service
            ├── sku
            │   ├── manager
            │   ├── model
            │   ├── repository
            │   └── service
            └── spu
                ├── api
                │   ├── converter
                │   │   ├── input
                │   │   └── output
                │   └── facade
                ├── manager
                ├── model
                ├── repository
                └── service
```
