###开发说明：
    * 1.接口参数以原型及数据库为准,如需改动,可自行修改
    * 2.用户自己各自负责的类需要在类上面写明作者及联系方式
    * 3./tmp的所有接口不实现
    * 4.表ID主键生成统一调用IDGenerator.getRandomID()
    * 5.针对单表的操作请使用mybatis generator生成的example编写,尽量避免新写mapper sql
    
###包结构说明：
    * api   controller  
        - form    请求表单类  
        - query    查询参数类      
        - vo    前端vo  
    * common    常用类  
    * config    配置  
    * contant   常量  
    * pojo    数据库模型  
    
    * dao    dao层
        - ext   扩展dao  
    * service   service实现
    

###代码规范
    * service层必须throw Exception  
    * controller层也可直接throw GlobalExceptionHandler中的对应的异常类型
    * 方法行数不可超过当前页面可视区域（注意拆分方法）
    * 所有controller、service方法都必须有注释，dao的方法不需要注释、不处理异常，要保证方法的单一性，不能有大型业务方法
    * 原生mapper接口和mapper.xml不可修改，添加自定义方法必须扩展，分别写在dao.ext和resources/mapper/ext下，命名统一原生mapper+"ext"
    
###新增说明
    * @DuplicateSubmitToken 自定义10秒之内防止用户重复提交注解 在需要controller上加上此注解
    * 增加了系统日志自定义注解 @ControllerLogs @ServiceLogs分别加在controller和service上 打印的参数为实体类SysLogs 
        在application.properties中配置save.log 和print.log控制日志行为
     
    