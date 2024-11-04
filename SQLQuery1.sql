create database DLLuong1
use DLLuong1

create table tbNhanvien (
manv nvarchar(50) primary key,
hoten nvarchar(100),
diachi nvarchar(100),
luong int);

insert into tbNhanVien values 
('100', 'bui khac huy', 'thanh hoa', 20000000),
('101', 'dinh quang dat', 'nghe an', 1000000),
('103', 'nguyen thanh dong', 'hai duong', 3000000),
('105', 'tran tien dat', 'nam dinh', 2340000);

select * from tbNhanvien
update tbNhanvien set hoten = N'Bui Khac Huy', diachi = N'hoa thanh', luong = 12345 where manv = '100' 
delete tbNhanvien where manv = '100'