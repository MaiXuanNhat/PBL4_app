# PBL4_app_automatic-door-with-face-mask-recognition-and-body-temperature
## About this project:
Đại dịch COVID19 đang diễn ra mạnh mẽ trên toàn cầu. Sự bùng phát COVID19 đã buộc các chính phủ trên thế giới phải thực hiện các biện pháp khóa cửa để ngăn chặn sự lây truyền 
của vi rút. Theo các báo cáo khảo sát, đeo khẩu trang ở những nơi công cộng làm giảm nguy cơ lây truyền bệnh một cách đáng kể. Biện pháp an toàn đơn giản và hữu hiệu nhất là đeo
khẩu trang ở những nơi công cộng. Xuất phát từ mong muốn hỗ trợ cho công tác phòng dịch, đề tài xây dựng một hệ thống cửa thông minh hỗ trợ IoT sử dụng mô hình máy học để theo
dõi nhiệt độ cơ thể và phát hiện mặt nạ được lên ý tưởng và thực hiện. Ứng dụng này được xây dựng nhằm mục đích hỗ trợ kiểm tra dữ liệu của hệ thống phòng dịch.
## Android App

### Home Fragment

![image](https://github.com/MaiXuanNhat/PBL4_app_automatic-door-with-face-mask-recognition-and-body-temperature/issues/1#issue-1083147267)

Giao diện màn hình chính bao gồm các nút nguồn để Bật/Tắt hệ thống, nút Đóng/Mở cửa thủ công, 2 nút chuyển đến giao diện Báo cáo chi tiết và Thống kê

### Report Fragment

![image](https://github.com/MaiXuanNhat/PBL4_app_automatic-door-with-face-mask-recognition-and-body-temperature/issues/2#issue-1083149431)

Giao diện báo cáo hiển thị dữ liệu của hệ thống được lưu trên Firebase. Dữ liệu gồm thời gian, nhiệt độ cũng như trạng thái đeo khẩu trang, rửa tay, mở cửa của người đứng trước hệ thống.
Người dùng có thể tìm kiếm dữ liệu theo thời gian với các trường hợp: Tìm kiếm toàn bộ, tìm kiếm với ngày bắt đầu, tìm kiếm với ngày kết thúc, tìm kiếm trong một khoảng 
thời gian.

### Detail Fragment

![image](https://github.com/MaiXuanNhat/PBL4_app_automatic-door-with-face-mask-recognition-and-body-temperature/issues/3#issue-1083151870)

Khi người dùng bấm vào một dòng dữ liệu được hiển thị trên giao diện báo cáo, ứng dụng sẽ chuyển đến giao diện chi tiết chứ hình ảnh người đứng trước hệ thống cũng như các thông
tin phòng dịch gồm thời gian, nhiệt độ cũng như trạng thái đeo khẩu trang, rửa tay, mở cửa của người đó.

### Statistical Fragment

![image](https://github.com/MaiXuanNhat/PBL4_app_automatic-door-with-face-mask-recognition-and-body-temperature/issues/4#issue-1083155868)

Giao diện thống kê hiện tống số người dùng hệ thống, số lượng người đeo khẩu trang, nhiệt độ trung bình, số người rửa tay, sô người được mở cửa cũng như tỉ lệ của chúng với tống số trong một khoảng thời gian được người dùng nhập vào.
