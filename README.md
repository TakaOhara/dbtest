#

### 設定ファイル  
application.properties を application.yml にリネーム
プロファイルごとのファイルを生成
* application-dev.yml : 開発用
* application-prod.yml : 本番用


* application.ymlにactive profileを指定する  
spring:profiles:active: dev     

* VM argumentsに-Dspring.profiles.active=devなどで指定する

### セキュリティ設定

### webjarsの利用

### コンストラクターインジェクション

### メッセージファイルの利用

### logback-spring.xmlの配置

### datasourceの設定
hikariCPがデフォルト  
設定ファイルだけで設定可能  

### thymeleaf-layout-dialectの利用
共通テンプレート  

### Test (JUNIT5)



