--
--    Copyright 2016-2017 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

drop table OrderDetail if exists;
drop table OrderMaster if exists;

create table OrderMaster (
   order_id int not null,
   order_date date not null,
   primary key(order_id)
);

create table OrderDetail (
   order_id int not null,
   line_number int not null,
   description varchar(30) not null,
   quantity int not null,
   primary key(order_id, line_number),
   foreign key(order_id) references OrderMaster(order_id)
);

insert into OrderMaster(order_id, order_date) values(1, '2017-01-17');
insert into OrderDetail(order_id, line_number, Description, quantity) values(1, 1, 'Tennis Ball', 3);
insert into OrderDetail(order_id, line_number, Description, quantity) values(1, 2, 'Tennis Racket', 1);

insert into OrderMaster (order_id, order_date) values(2, '2017-01-18');
insert into OrderDetail(order_id, line_number, Description, quantity) values(2, 1, 'Football', 2);