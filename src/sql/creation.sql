use findit;

create table user (
    id integer not null auto_increment,
    version integer not null,
    username varchar(50) not null,
    password varchar(50) not null,

    primary key (id)
);

create table role (
    id integer not null auto_increment,
    version integer not null,
    authority varchar(20) not null,

    primary key (id)
);

create table user_role (
    user_id integer not null,
    role_id integer not null,

    foreign key (user_id) references user(id),
    foreign key (role_id) references role(id)
);

create table team (
    id integer not null auto_increment,
    version integer not null,
    teamname varchar(50) not null,
    creator integer not null,

    primary key (id),
    foreign key (creator) references user(id)
);

create table team_user (
    team_id integer not null,
    user_id integer not null,

    foreign key (team_id) references team(id),
    foreign key (user_id) references user(id)
);

create table location (
    id int(11) NOT NULL AUTO_INCREMENT,
    version int(11) NOT NULL,

    PRIMARY KEY (id)
);

create table disk_location (
    id integer not null,
    on_disk_name varchar(200) not null,

    primary key (id),
    foreign key (id) references location(id)
);

create table physical_location (
    id integer not null,
    name varchar(200) not null,
    description varchar(500),

    primary key (id),
    foreign key (id) references location(id)
);

create table item (
    id integer not null auto_increment,
    version integer not null,
    owner_id integer not null,
    location_id integer not null,
    name varchar(200) not null,
    date_created date not null,
    date_updated date not null,
    type varchar(20),
    size integer,
    event_start_time date,
    event_end_time date,
    people varchar(5000),
    places varchar(5000),
    description varchar(5000),

    primary key (id),
    foreign key (owner_id) references user(id),
    foreign key (location_id) references location(id)
);