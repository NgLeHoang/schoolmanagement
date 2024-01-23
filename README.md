# Hướng dẫn chi tiết về chương trình
## Video mẫu chạy thử chương trình quản lý trường học bằng JAVA
### Link youtube: https://youtu.be/4Afbga72cpo

## Chương trình sử dụng JAVA SWING trên eclipse và database là MySQL chạy trên XAMPP Control
### Tải về file database trong src/mysql 
Sau đó mở file database lên và chạy để tạo database cho chương trình
> Lưu ý: Dự án này chỉ chạy được trên Eclipse và nếu dùng công cụ khác như NetBean hay Intellij thì cần phải tự kéo lại giao diện,
> còn logic đã có sẵn trong chương trình, chỉ cần hiểu và chắt lọc ra là được.
## Tổng quát về chương trình
- Chương trình có các chức năng cơ bản theo mô hình ***CRUD*** **(Create - Read - Update - Delete)**.
- Chương trình sẽ có 4 quyền chính với 4 cửa sổ khác nhau như *Admin, Staff, Teacher, Student* được truyền dữ liệu thông qua dữ liệu **number** từ bảng User.
- Đầu tiên tạo một user có quyền là admin, với quyền là admin thì sẽ truy cập được mọi chương trình quản lý trong ứng dụng như:
**Quản lý Staff, Teacher, Student, tạo User, quản lý lớp học, môn học, lịch học, kỳ thi, điểm số**.
- Mở cửa sổ chạy chương trình mặc định là form **Login**.
- Từ đó dùng user với quyền là **admin** đăng nhập vào.
- Từ cửa sổ của admin truy cập vào tạo user.
> Lưu ý: Trong bảng user có dữ liệu **number**, dữ liệu này sẽ tương ứng với **staffnumber, teachernumber và studentnumber** trong các bảng
> **staff, teacher, student** dùng để hiển thị chính xác thông tin lúc đăng nhập sẽ truyền đến và hiển thị lên form với quyền tương ứng.
- Về cơ bản các cửa sổ **quản lý staff, quản lý teacher, quản lý student** sẽ có các chức năng tương tự theo mô hình ***CRUD***.
Dữ liệu sẽ được tương tác với database và hiển thị lên bảng trong chương trình, khi nhấn vào một dòng dữ liệu trên bảng thì
dữ liệu sẽ được truyền lại các ô nhập thông tin để chỉnh sửa.
- Cửa sổ quản lý class, subject cũng sẽ tương tự.
- Ở cửa sổ **quản lý lịch học**, thông tin **teachername, classname và subject name** sẽ được truyền đến từ các bảng **teacher, class, subject**.
Tương tự nó cũng sẽ có các chức năng theo mô hình ***CRUD***.
- Ở cửa sổ **quản lý lịch thi**, thông tin **classname và subjectname** cũng được truyền đến như cửa sổ **quản lý lịch học**.
- Cuối cùng là cửa sổ **quản lý điểm thi**, ở đây có chức năng tìm kiếm thông tin **student** thông qua **studentnumber**, từ đó chuẩn hóa
thông tin đúng đắn hơn.
- Với user đăng nhập quyền **Staff**, chức năng cũng tương tự với admin trừ việc **quản lý staff và tạo user**.
Ở đây sẽ có thêm phần hiển thị thông tin của **staff** được truyền đến thông qua **number** đã nói đến ở trên.
- Với user đăng nhập quyền **Teacher**, quyền chỉ có mỗi việc chấm điểm, các bạn có thể custom lại và thêm quyền truy cập
vào quản lý lịch học chẳng hạn. Cửa sổ cũng sẽ hiển thị thông tin của **teacher** như cách ở trên.
- Cuối cùng là user đăng nhập với quyền **Student**, quyền của student sẽ xem được thông tin lịch học, có chức năng lọc thông tin lịch học
thông qua **classname** và thông tin của student cũng tương tự truyền đến như trên.
# Và đó là tổng quát chương trình quản lý trường học mà mình đã làm. Nếu sau này có thời gian rảnh mình sẽ quay lại cập nhập chi tiết các lớp hoạt động của chương trình hơn.
# Cảm ơn các bạn đã tham khảo. Nếu thấy hay nhớ để lại cho mình 1 star nhé!

