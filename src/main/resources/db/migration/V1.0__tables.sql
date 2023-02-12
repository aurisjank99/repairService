create table if not exists department (
    id                      bigint          auto_increment  NOT NULL,
    name                    varchar         NOT NULL,
    type                    varchar         NOT NULL,
    CONSTRAINT department_pkey PRIMARY KEY (id)
);

create table if not exists request (
    id                      bigint          auto_increment  NOT NULL,
    request_type            varchar         NOT NULL,
    department_id           varchar         NOT NULL,
    start_date              timestamp       NOT NULL,
    end_date                timestamp       NOT NULL,
    currency                varchar         NOT NULL,
    amount                  numeric(19, 2)  NOT NULL,
    CONSTRAINT request_pkey PRIMARY KEY (id),
    CONSTRAINT request_department_fkey FOREIGN KEY (department_id) REFERENCES department (id)
);

create table if not exists request_parts (
    id                      bigint          auto_increment  NOT NULL,
    request_id              bigint          NOT NULL,
    inventory_number        varchar         ,
    name                    varchar         ,
    count                   bigint          ,
    CONSTRAINT request_parts_pkey PRIMARY KEY (id),
    CONSTRAINT request_parts_part_fkey FOREIGN KEY (request_id) REFERENCES request (id)
);

create table if not exists request_details_repair (
    id                      bigint          auto_increment  NOT NULL,
    request_id              bigint          NOT NULL,
    analysis_date           timestamp       NULL,
    test_date               timestamp       NOT NULL,
    responsible_person      varchar         NOT NULL,
    CONSTRAINT request_details_repair_pkey PRIMARY KEY (id),
    CONSTRAINT request_details_repair_request_fkey FOREIGN KEY (request_id) REFERENCES request (id)
);

create table if not exists request_details_replacement (
    id                      bigint          auto_increment  NOT NULL,
    request_id              bigint          NOT NULL,
    factory_name            varchar         NOT NULL,
    factory_order_number    varchar         NOT NULL,
    CONSTRAINT request_details_replacement_pkey PRIMARY KEY (id),
    CONSTRAINT request_details_replacement_request_fkey FOREIGN KEY (request_id) REFERENCES request (id)
);

create table if not exists work_order_request (
    id                      bigint          auto_increment  NOT NULL,
    received_date           timestamp       NOT NULL,
    request_type            varchar         NULL,
    department              varchar         NULL,
    status                  varchar         NOT NULL,
    CONSTRAINT work_order_request_pkey PRIMARY KEY (id)
);