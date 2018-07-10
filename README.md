# java-api-doc
	目前此接口文档生成markdown文件，后续如果要生成其它类型文件，可扩展，如要使用此工具生成接口文档，需要完成以下步聚：
	一. 代码模版导入
	1.eclipse打开Preferences->java->Code Style;
	2.选择Clean Up点击Import导入当前程resources下的owner-clean-up.xml,点击Apply
	3.选择Code Templates 点击Import导入当前程resources下的owner-code-templates.xml,
	点击Apply(此处用户名${user}取的系统用户，如果你的系统用户不是你的名字，
	可直接将所有${user写死或打开eclipse目录下的eclipse.ini文件，添加上一行。-Duser.name="yourname"})
	4.选择Code Formatter 点击Import导入当前程resources下的owner-formatter.xml,点击Apply
	二. 规范与操作
	1.接口返回值必须都是返回Result<?>,
	2.每个接口方法创建后，在方法上行输入/**后回车，以下为添加的注释示例,descripton一定要填，此描述此方法提供的服务内容
	/**
     * 
     * description: TODO 
     * @param ints
     * @return
     * createdBy:zhuangjianfa            
     * created:2018年3月9日
     */
     3.请求参数类或返回值类上的属性注释会有些麻烦，需要光标定在属性行按快捷键alt+shift+j才能添加属性注释，以下为示例注释，描述强制要填，remark在有特殊说明的情况下选填
    /**
     * description:应用名称
     * remark:
     */
     4.请求参数属性必填的判断依据是包含@NotEmpty,@NotBlank,@NotNull的注释
     5.所有请求参数与返回值都要序列化,不影响生成文档，此为代码规范
     6.每个工程对外提供的接口的所有请求参数对象与返回值对象(除Result)，接口对象都分别放在各自独立的路径下。
             三. 生成接口文档操作
     1. 修改resources下的配置文件application.yml,按自己要求配置
     2. 运行单元测试GenerateDocHtmlTest就可以。
     3. 在tapd(https://www.tapd.cn/20224921/markdown_wikis/view/#1120224921001002211)新建你模块wiki页、服务wiki页,编辑服务wiki页，将文件内容复制进行即可。
# 注:如果在使用过程中发现有问题时，请向架构组反馈问题，由架构组确认问题并解决，谢谢合作！！！
     
	