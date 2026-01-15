create table quiz_question (
    id int auto_increment primary key,
    question varchar(255) not null,
    option_a varchar(100) not null,
    option_b varchar(100) not null,
    option_c varchar(100) not null,
    option_d varchar(100) not null,
    correct_option char(1)not null,
);

create table quiz_result (
    id int auto_increment primary key,
    username varchar(100) not null,
    score int not null ,
    total int not null ,
    created_at timestamp default current_timestamp
);
