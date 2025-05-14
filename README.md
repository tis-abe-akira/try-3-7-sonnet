# try-3-7-sonnet プロジェクト概要

## ✨ プロジェクト概要

このプロジェクトは、Spring Boot 3.2.1・Java 17・MyBatis・H2 Databaseを使ったサンプルWebアプリだよ！
顧客（Customer）とプロジェクト（Project）のマスタ管理をREST APIでCRUDできるギャル的な設計になってるよ〜。

## 🏗️ 技術スタック
- Spring Boot 3.2.1
- Java 17
- MyBatis 3.0.4
- H2 Database（インメモリDB）
- Spring Validation（バリデーション）
- Spring AOP（ロギング等）
- Lombok
- springdoc-openapi（Swagger UI）

## 📁 ディレクトリ構成
```
src/main/java/com/example/try_3_7_sonnet/
├── common/              # 共通コンポーネント
│   ├── advisor/        # 例外ハンドリング
│   ├── aop/           # アスペクト（ロギング）
│   ├── config/        # 設定クラス
│   ├── dto/           # データ転送オブジェクト
│   ├── entity/        # ドメインエンティティ
│   ├── exception/     # カスタム例外
│   ├── handler/       # ハンドラー
│   ├── repository/    # データアクセス
│   ├── service/       # ビジネスロジック
│   ├── typehandler/   # MyBatis型ハンドラー
│   └── util/          # ユーティリティ
└── feature/           # 機能モジュール
    ├── customer/      # 顧客管理API
    │   ├── controller/  # コントローラー
    │   ├── dto/         # DTOクラス
    │   ├── entity/      # エンティティ
    │   ├── repository/  # リポジトリ
    │   └── service/     # サービス
    └── project/       # プロジェクト管理API
        ├── controller/  # コントローラー
        ├── dto/         # DTOクラス
        ├── entity/      # エンティティ
        ├── repository/  # リポジトリ
        └── service/     # サービス
```

## 📝 主なエンティティ
- **Customer**: 名前・連絡先・業種
- **Project**: Customer参照、担当部署・PM・PL・ランク（S/A/B/C/D）・開始日・終了日・区分（新規/保守/パッケージ）

## 🚀 REST API
- `/api/customers` ... 顧客CRUD・ページング機能付き
- `/api/projects` ... プロジェクトCRUD

## 💡 開発Tips
- DBスキーマは `src/main/resources/schema.sql` で自動生成
- MyBatisマッパーは `src/main/resources/mapper/` 配下
- バリデーションは `jakarta.validation.constraints` を使ってるよ！
- 列挙型はMyBatisの型ハンドラーでカスタム変換
- ページングは `PageResponse` と `PaginationResponse` を活用
- Swagger UIは `/swagger-ui.html` でアクセスできる（springdoc-openapi）

## 🏃‍♀️ 起動方法
```bash
./mvnw clean spring-boot:run
```

## 🦋 注意点
- Spring Boot/Springdocのバージョン互換に注意してね！
- JSONリクエストは正しい形式で送ってね！

---
ギャル的に質問あったらいつでも聞いてね！
