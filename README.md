# ToDoIt - Ứng dụng Quản lý Công việc

Ứng dụng ToDoIt là một hệ thống quản lý công việc đơn giản, giúp người dùng theo dõi, tạo mới, cập nhật và xóa các công việc cần làm.

## Tính năng chính
- Đăng nhập/Đăng ký tài khoản
- Xem danh sách công việc cần làm và đã hoàn thành
- Tạo công việc mới
- Chỉnh sửa thông tin công việc
- Đánh dấu công việc đã hoàn thành
- Xóa công việc
- Tìm kiếm công việc
- Sắp xếp công việc theo nhiều tiêu chí khác nhau

## Yêu cầu hệ thống
- Java JDK 17 hoặc cao hơn
- Maven 3.6.3 hoặc cao hơn
- MySQL 8.0 hoặc cao hơn
- Spring Boot 3.x

## Cách cài đặt

### 1. Chuẩn bị cơ sở dữ liệu
1. Tạo database MySQL với tên `todoit_db` (hoặc tên khác theo cấu hình trong `application.properties`)
```sql
CREATE DATABASE todoit_db;
```

2. Cấu hình kết nối database trong file `src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todoit_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### 2. Cài đặt và chạy ứng dụng
Có hai cách để chạy ứng dụng:

#### Cách 1: Sử dụng Maven
1. Mở Terminal/Command Prompt tại thư mục gốc của dự án
2. Chạy lệnh sau để biên dịch và đóng gói ứng dụng:
```
mvnw clean package
```
3. Chạy ứng dụng với lệnh:
```
java -jar target/PhamThanhHieu_WebToDoList-0.0.1-SNAPSHOT.jar
```

#### Cách 2: Sử dụng IDE (Eclipse, IntelliJ IDEA, VS Code)
1. Import dự án vào IDE như một Maven project
2. Tìm và chạy file `src/main/java/edu/ute/PhamThanhHieu_WebToDoList/PhamThanhHieuWebToDoListApplication.java`

### 3. Truy cập ứng dụng
Sau khi ứng dụng khởi động, truy cập trang web qua URL:
```
http://localhost:8080
```

## Tài khoản mặc định
Đăng nhập với tài khoản mặc định
- Email: admin@gmail.com
- Password: 1234

## Hướng dẫn sử dụng
1. **Đăng nhập/Đăng ký**: Sử dụng trang đăng nhập để truy cập hệ thống hoặc đăng ký tài khoản mới
2. **Trang chủ**: Hiển thị danh sách công việc cần làm
3. **Công việc đã hoàn thành**: Xem danh sách các công việc đã hoàn thành
4. **Thêm công việc mới**: Nhấn vào nút "Thêm Task" để tạo công việc mới
5. **Sửa công việc**: Nhấn nút "Sửa task" bên cạnh mỗi công việc để chỉnh sửa
6. **Hoàn thành công việc**: Nhấn nút "Xong task" để đánh dấu công việc đã hoàn thành
7. **Xóa công việc**: Nhấn vào biểu tượng thùng rác để xóa công việc
8. **Tìm kiếm**: Sử dụng ô tìm kiếm để tìm công việc theo từ khóa
9. **Sắp xếp**: Chọn các tùy chọn sắp xếp (Mới nhất, Ngày hết hạn, Độ ưu tiên)

## Cấu trúc dự án
- `src/main/java/edu/ute/PhamThanhHieu_WebToDoList/controller`: Chứa các controller xử lý request
- `src/main/java/edu/ute/PhamThanhHieu_WebToDoList/model`: Chứa các entity/model
- `src/main/java/edu/ute/PhamThanhHieu_WebToDoList/service`: Chứa business logic
- `src/main/java/edu/ute/PhamThanhHieu_WebToDoList/repo`: Chứa các repository để tương tác với database
- `src/main/resources/templates`: Chứa các template HTML
- `src/main/resources/static`: Chứa các tài nguyên tĩnh (CSS, JS, images)

## Xử lý lỗi thường gặp
1. **Lỗi kết nối database**: Kiểm tra thông tin kết nối trong `application.properties`
2. **Lỗi server port đã được sử dụng**: Thay đổi port trong `application.properties` với `server.port=8081` (hoặc port khác)
3. **Lỗi không tìm thấy Java**: Đảm bảo biến môi trường JAVA_HOME đã được cấu hình đúng

## Tác giả
Phạm Thanh Hiếu