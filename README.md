# hello_play_scalikejdbc

- scalikejdbcを使ったデータベース操作・接続を学ぶ。 
- scalikejdbc-mapper-generatorの使い方を学ぶ。
- MySQLサンプルデータベースのworldデータベースに基づくmodelの自動生成。
- MySQL 8.0.22を使用した。認証方式の変化があり多少詰まったが、mysql-connector-javaのバージョンがシステムのMySQLと対応していることがわかり、対応をきちんととると問題なく接続可能であった。
- Webアプリケーションとして、ホスティングしたサーバ上でのデータベースの接続、play applicationの実行を学んでいきたい。

## References

- scalikejdbc-mapper-generator:
  - https://github.com/scalikejdbc/scalikejdbc-cookbook/blob/master/ja/09_source_code_generator.md
  - http://scalikejdbc.org/documentation/setup.html
- MySQL認証方式
  - https://qiita.com/RollSystems/items/9c266a283cee718b2f0b
- Sample database by MySQL
  - https://dev.mysql.com/doc/index-other.html