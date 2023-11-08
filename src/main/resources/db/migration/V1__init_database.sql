create table discipline
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
);

create table group_discipline
(
    discipline_id bigint not null,
    group_id      bigint not null
);

create table student_group
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
);

create table student_test
(
    id            bigint not null auto_increment,
    name          varchar(255),
    date_end      date,
    is_available  bit,
    discipline_id bigint,
    primary key (id)
);

create table test_answer
(
    id          bigint not null auto_increment,
    text        varchar(255),
    is_correct  bit,
    question_id bigint,
    primary key (id)
);

create table test_question
(
    id      bigint not null auto_increment,
    title   varchar(255),
    test_id bigint,
    primary key (id)
);

create table test_result
(
    id         bigint not null auto_increment,
    score      float(53),
    student_id bigint,
    test_id    bigint,
    primary key (id)
);

create table test_user
(
    test_id bigint not null,
    user_id bigint not null,
    primary key (test_id, user_id)
);

create table user_role
(
    user_id bigint not null,
    roles   varchar(20)
);

create table usr
(
    id         bigint not null auto_increment,
    username   varchar(255),
    password   varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    patronymic varchar(255),
    group_id   bigint,
    primary key (id)
);

alter table group_discipline
    add constraint group_discipline_student_group_fk foreign key (group_id) references student_group (id);
alter table group_discipline
    add constraint group_discipline_discipline_fk foreign key (discipline_id) references discipline (id);
alter table student_test
    add constraint test_discipline_fk foreign key (discipline_id) references discipline (id);
alter table test_answer
    add constraint answer_question_fk foreign key (question_id) references test_question (id);
alter table test_question
    add constraint question_test_fk foreign key (test_id) references student_test (id);
alter table test_result
    add constraint result_user_fk foreign key (student_id) references usr (id);
alter table test_result
    add constraint result_test_fk foreign key (test_id) references student_test (id);
alter table test_user
    add constraint test_user_user_fk foreign key (user_id) references usr (id);
alter table test_user
    add constraint test_user_student_test_fk foreign key (test_id) references student_test (id);
alter table user_role
    add constraint fk_user_role foreign key (user_id) references usr (id);
alter table usr
    add constraint user_group_fk foreign key (group_id) references student_group (id);
