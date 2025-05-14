CREATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(100),
    industry VARCHAR(50),
    address VARCHAR(200),
    notes VARCHAR(500),
    created_by BIGINT,
    created_at TIMESTAMP,
    updated_by BIGINT,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE project (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    customer_id BIGINT NOT NULL,
    department_name VARCHAR(50),
    project_manager_name VARCHAR(50),
    project_leader_name VARCHAR(50),
    rank VARCHAR(1) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    project_type VARCHAR(50) NOT NULL,
    description VARCHAR(500),
    created_by BIGINT,
    created_at TIMESTAMP,
    updated_by BIGINT,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- サンプルデータ投入
-- INSERT INTO customer (name, phone_number, email, industry, address, notes, created_at, updated_at, is_deleted)
-- VALUES 
-- ('株式会社テストカンパニー', '03-1234-5678', 'info@testcompany.co.jp', 'IT', '東京都渋谷区テスト1-2-3', 'テスト顧客データ', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE),
-- ('サンプル商事', '06-8765-4321', 'contact@sample.co.jp', '製造業', '大阪府大阪市中央区サンプル町4-5-6', 'サンプル顧客データ', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE),
-- ('デモ銀行', '045-123-4567', 'support@demobank.co.jp', '金融', '神奈川県横浜市港北区デモ7-8-9', 'デモ顧客データ', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE);

-- INSERT INTO project (name, customer_id, department_name, project_manager_name, project_leader_name, rank, start_date, end_date, project_type, description, created_at, updated_at, is_deleted)
-- VALUES 
-- ('基幹システム再構築', 1, 'IT部門', '山田太郎', '佐藤次郎', 'A', '2023-01-01', '2023-12-31', '新規開発', '老朽化した基幹システムの刷新', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE),
-- ('モバイルアプリ開発', 1, 'デジタル戦略部', '鈴木花子', '高橋一郎', 'B', '2023-03-15', '2023-09-30', '新規開発', '顧客向けモバイルアプリの開発', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE),
-- ('セキュリティ対策強化', 2, 'IT部門', '伊藤直樹', '中村洋子', 'S', '2023-02-01', '2023-05-31', '保守開発', 'セキュリティ脆弱性対応とインフラ強化', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE),
-- ('ERPパッケージ導入', 3, '経営企画部', '小林誠', '加藤美咲', 'A', '2023-04-01', '2024-03-31', 'パッケージ導入', '業務効率化のためのERP導入', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE);
