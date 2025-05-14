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

INSERT INTO customer (name, phone_number, email, industry, address, notes, created_at, updated_at, is_deleted)
VALUES 
('Test Company Ltd.', '03-1234-5678', 'info@testcompany.com', 'IT', '1-2-3 Test Street, Shibuya, Tokyo', 'Test customer data', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE),
('Sample Trading Co.', '06-8765-4321', 'contact@sample.com', 'Manufacturing', '4-5-6 Sample Town, Chuo, Osaka', 'Sample customer data', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE),
('Demo Bank', '045-123-4567', 'support@demobank.com', 'Finance', '7-8-9 Demo District, Kohoku, Yokohama', 'Demo customer data', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE);

INSERT INTO project (name, customer_id, department_name, project_manager_name, project_leader_name, rank, start_date, end_date, project_type, description, created_at, updated_at, is_deleted)
VALUES 
('Core System Renewal', 1, 'IT Department', 'Taro Yamada', 'Jiro Sato', 'A', '2023-01-01', '2023-12-31', 'NEW_DEVELOPMENT', 'Renewal of aging core system', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE),
('Mobile App Development', 1, 'Digital Strategy Department', 'Hanako Suzuki', 'Ichiro Takahashi', 'B', '2023-03-15', '2023-09-30', 'NEW_DEVELOPMENT', 'Development of a customer-facing mobile app', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE),
('Security Enhancement', 2, 'IT Department', 'Naoki Ito', 'Yoko Nakamura', 'S', '2023-02-01', '2023-05-31', 'NEW_DEVELOPMENT', 'Addressing security vulnerabilities and strengthening infrastructure', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), FALSE);
